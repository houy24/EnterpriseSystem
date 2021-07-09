function GetSelect(){
    var obj = document.getElementById("departments");
    var index = obj.selectedIndex;
    var value = obj.options[index].value;

    $.ajax({
        url: "/PersonalInformationDepartment",
        data: {
            departments: value
        },
        dataType : "json",
    })
}