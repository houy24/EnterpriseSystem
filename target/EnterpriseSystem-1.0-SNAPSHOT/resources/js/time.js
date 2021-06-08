//时间
function fn(){
    let time = new Date();
    let str= "";
    let div = document.getElementById("time");
//    console.log(time);
    let year = time.getFullYear();
    let mon = time.getMonth()+1;
    let day = time.getDate();
    let h = time.getHours();
    let m = time.getMinutes();
    let s = time.getSeconds();
    let week = time.getDay();
    switch (week){
        case 0:week="日";
            break;
        case 1:week="一";
            break;
        case 2:week="二";
            break;
        case 3:week="三";
            break;
        case 4:week="四";
            break;
        case 5:week="五";
            break;
        case 6:week="六";
            break;
    }
    str = year +"年"+totwo(mon)+"月"+totwo(day)+"日"+" "+totwo(h)+":"+totwo(m)+":"+totwo(s)+" "+"星期"+week;
    $('#time').text(str);
}

fn();

setInterval(fn,1000);

function totwo(n){
    if(n<=9){
        return n = "0"+n;
    }
    else{
        return n =""+n;
    }
}

