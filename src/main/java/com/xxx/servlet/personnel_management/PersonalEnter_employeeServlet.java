package com.xxx.servlet.personnel_management;

import com.xxx.pojo.*;
import com.xxx.service.PersonalDepartment.PersonalDepartmentService;
import com.xxx.service.PersonalDepartment.PersonalDepartmentServiceImpl;
import com.xxx.service.Position.PositionService;
import com.xxx.service.Position.PositionServiceImpl;
import com.xxx.service.UserAccount.UserAccountService;
import com.xxx.service.UserAccount.UserAccountServiceImpl;
import com.xxx.service.UserData.UserDataService;
import com.xxx.service.UserData.UserDataServiceImpl;
import com.xxx.service.WorkTitleDao.WorkTitleDaoService;
import com.xxx.service.WorkTitleDao.WorkTitleDaoServiceImpl;
import com.xxx.utils.UUIDUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/PersonalEnter_employee")
public class PersonalEnter_employeeServlet extends HttpServlet {
    static String path;
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("utf-8");
        System.out.println("============进入 PersonalEnter_employeeServlet============");
        UserAccount userAccount = (UserAccount) request.getSession().getAttribute("userAccount");
        System.out.println("用户："+userAccount);

        String userId = UUIDUtils.getUUIDArg("user-");
        //得到上传文件的保存目录，将上传的文件存放于WEB-INF目录下，不允许外界直接访问，保证上传文件的安全
        String savePath = this.getServletContext().getRealPath("/resources/images");
        System.out.println(savePath);
        //消息提示
        Map<String,String> map = new HashMap<>();

        try{
            //使用Apache文件上传组件处理文件上传步骤：
            //1、创建一个DiskFileItemFactory工厂
            DiskFileItemFactory factory = new DiskFileItemFactory();
            //2、创建一个文件上传解析器
            ServletFileUpload upload = new ServletFileUpload(factory);
            //解决上传文件名的中文乱码
            upload.setHeaderEncoding("UTF-8");
            //3、判断提交上来的数据是否是上传表单的数据
            if(!ServletFileUpload.isMultipartContent(request)){
                //按照传统方式获取数据
                return;
            }
            //4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
            List<FileItem> list = upload.parseRequest(request);

            for(FileItem item : list){
                //如果fileitem中封装的是普通输入项的数据
                if(item.isFormField()){
                    String name = item.getFieldName();
                    //解决普通输入项的数据的中文乱码问题
                    String value = item.getString("UTF-8");
                    //value = new String(value.getBytes("iso8859-1"),"UTF-8");
                    System.out.println(name + "=" + value);
                    map.put(name,value);
                }else{//如果fileitem中封装的是上传文件
                    //得到上传的文件名称，
                    String filename = item.getName();
                    System.out.println(filename);
                    if(filename==null || filename.trim().equals("")){
                        continue;
                    }
                    //注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如：  c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
                    //处理获取到的上传文件的文件名的路径部分，只保留文件名部分
                    filename = filename.substring(filename.lastIndexOf("\\")+1);
                    //获取item中的上传文件的输入流
                    InputStream in = item.getInputStream();
                    //创建一个文件输出流
                    FileOutputStream out = new FileOutputStream(savePath + "\\"+userId+"-"+ filename);
                    //创建一个缓冲区
                    byte[] buffer = new byte[1024];
                    //判断输入流中的数据是否已经读完的标识
                    int len;
                    //循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
                    while((len=in.read(buffer))>0){
                        //使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\" + filename)当中
                        out.write(buffer, 0, len);
                    }
                    path = "/resources/images/" +userId+"-"+ filename;
                    //关闭输入流
                    in.close();
                    //关闭输出流
                    out.close();
                    //删除处理文件上传时生成的临时文件
                    item.delete();
                }
            }
        }catch (Exception e) {
            e.printStackTrace();

        }

        System.out.println("图片路径："+path);

        String employeeName = map.get("employeeName");
        System.out.println("姓名："+employeeName);
        String userName = map.get("employeeName");
        String userPhone = map.get("employeePhone");
        String userSex = map.get("employeeSex");
        String userEmail = map.get("employeeEmail");
        String userAge = map.get("employeeAge");
        String workAge = map.get("employeeWorkAge");
        String userType = map.get("employeeType");
        String employeeDepartment = map.get("employeeDepartment");
        String employeePosition = map.get("employeePosition");
        String employeeWorkTitle = map.get("employeeWorkTitle");

        UserData userData = new UserData();
        //部门
        PersonalDepartmentService personalDepartmentService = new PersonalDepartmentServiceImpl();
        Department department = personalDepartmentService.selectAllByDepartmentName(employeeDepartment);
        //职位
        PositionService positionService = new PositionServiceImpl();
        Position position = positionService.selectAllByPositionName(employeePosition);
        //职称
        WorkTitleDaoService workTitleDaoService = new WorkTitleDaoServiceImpl();
        WorkTitle workTitle = workTitleDaoService.selectAllByWorkTitleName(employeeWorkTitle);

        userData.setUserId(userId);
        userData.setUserPhone(userPhone);
        userData.setUserName(userName);
        userData.setUserSex(userSex);
        userData.setUserEmail(userEmail);
        userData.setUserAge(Integer.parseInt(userAge));
        userData.setUserType(userType);
        userData.setDepartmentId(department.getDepartmentId());
        userData.setPositionId(position.getPositionId());
        userData.setWorkTitleId(workTitle.getWorkTitleId());
        userData.setWorkAge(Integer.parseInt(workAge));
        userData.setUserPhoto(path);

        System.out.println(userData.toString());

        UserAccount userAccount1 = new UserAccount(userId, userPhone, "123456", userType);
        UserDataService userDataService = new UserDataServiceImpl();
        UserAccountService userAccountService = new UserAccountServiceImpl();
        userAccountService.addUserAccount(userAccount1);

        int flag = userDataService.insert(userData);

        if (flag == 1){
            System.out.println("添加成功！");
        }else {
            System.out.println("添加失败！");
        }

        request.getRequestDispatcher("/pages/personnel_management/BackPersonalEnter_employee.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);
    }
}
