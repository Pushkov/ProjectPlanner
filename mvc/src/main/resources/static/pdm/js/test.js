$(function () {

    console.log('t');
    tableHeader();
    tableBody2(designers);
    // table_clear();
});

function table_clear() {
    document.getElementById("test_table").getElementsByTagName('tbody')[0].innerHTML = '';
}

function tableHeader() {
    $("#test_table").append(
        "<thead><tr>" +
        "<th>Login</th><th>" +

        "Имя" +

        "</th><th>Фамилия</th><th>Бюро</th><th>Телефон</th><th>Должность</th><th> </th>" +
        "</tr></thead>" +
        "<tbody></tbody>"
        // document.getElementById("test_table").getElementsByTagName('tbody')[0]
    );
}

function tableBody(list) {
    $("#test_table").append('<tbody>');
    for (var i = 0; i < list.length; i++) {
        var btnId = "btn" + list[i].id;
        var fname = "form" + list[i].id;
        $("#test_table tr:first").after(
            "<tr><form id='" + fname + "' action='/js/" + user.id + "/users/" + list[i].id + "' method='get' >" +
            "<td>" + list[i].id + "</td>" +
            "<td>" + list[i].firstName + "</td>" +
            "<td>" + list[i].lastName + "</td>" +
            "<td>" + list[i].office.name + "</td>" +
            "<td>" + list[i].phone + "</td>" +
            "<td>" + list[i].role + "</td>" +
            "<td>" +
            "<button class='button_jqui' style='font-size: .85em' type='submit' onclick='submit()' " +
            "id='" + btnId + "' " +
            ">bb</abutton></td>" +
            "</form></tr>");
        var sendButton = document.getElementById(btnId);
        sendButton.addEventListener("click", function () {
            var form = document.getElementById(fname);
            form.submit();
        });
    }
    ;
    $("#test_table").append('</tbody>');
};

function tableBody2(list) {
    for (var i = 0; i < list.length; i++) {
        addRow('test_table', list[i]);
    }
    ;
};

function addRow(id, user) {
    var tbody = document.getElementById(id).getElementsByTagName("TBODY")[0];
    var row = document.createElement("TR")
    var keys = [user.login, user.firstName, user.lastName, user.office.name, user.phone, user.role];

    var form = document.createElement('form');

    for (kk of keys) {
        var td1 = document.createElement("TD");
        td1.appendChild(document.createTextNode(kk));
        row.appendChild(td1);
    }

    var td2 = document.createElement("TD");
    var btn = document.createElement('button');
    btn.innerText = "btn";
    td2.appendChild(btn);
    row.appendChild(td2);
    tbody.appendChild(row);
}

/*
    var tr = document.createElement('tr');
    var td = tr.appendChild(document.createElement('td'));
    td.style.valign = 'middle';

    var span = td.appendChild(document.createElement('span'));
    span.style.fontWeight = 'bold';
    span.appendChild(document.createTextNode('URL ' + i));

    td = tr.appendChild(document.createElement('td'));
    var input = td.appendChild(document.createElement('input'));
    input.name = 'url' + ++i;
    input.type = 'text';
    input.size = '40'

    document.getElementById('myTable').tBodies[0].appendChild(tr);

}

 */