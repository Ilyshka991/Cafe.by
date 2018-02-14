function incrementProduct(id) {
    width = screen.width;
    if (width < 500) {
        var name = "number-for-add-" + id;
        var i = document.getElementById(name).value;
        if (i == 10) {
            document.getElementById(name).value = 0;
        } else {
            i = +i + 1;
            document.getElementById(name).value = i;
        }
    }
    return false;
}

function incrementProductDelete(id, max) {
    width = screen.width;
    if (width < 500) {
        var name = "number-for-delete-" + id;
        var i = document.getElementById(name).value;
        if (i == max) {
            document.getElementById(name).value = 0;
        } else {
            i = +i + 1;
            document.getElementById(name).value = i;
        }
    }
    return false;
}

function incrementBonus(max) {
    width = screen.width;
    if (width < 500) {
        var name = "point-to-payment";
        var i = document.getElementById(name).value;
        if (i == max) {
            document.getElementById(name).value = 0;
        } else {
            i = +i + 0.05;
            document.getElementById(name).value = i;
        }
    }
    return false;
}

function fileclick(id) {
    document.getElementById('file-edit-' + id).click();
}

function changeImage(id) {
    var filename = document.getElementById("file-edit-" + id).value.replace(/.*\\/, "");
    document.getElementById("image-name-edit-" + id).value = filename;
}

function checkEditInformation(id) {
    var name_en = document.getElementById("name-en-edit-" + id).value;
    var product_type = document.getElementById('product-type-edit-' + id).value;
    var name_ru = document.getElementById('name-ru-edit-' + id).value;
    var value = document.getElementById('value-edit-' + id).value;
    var cost = document.getElementById('cost-edit-' + id).value;
    var image_name = document.getElementById('image-name-edit-' + id).value;

    var name_ruReg = new RegExp('^([\u{0410}-\u{042F}]{1}[\u{0430}-\u{044F}\u{0410}-\u{042F}\\-\\s\\`]+)$');
    var name_enReg = new RegExp('^([A-Z]{1}[a-zA-Z-\\s\\-\\`]+)$');
    var cost_valueReg = new RegExp('^(([0-9]+)(\\.){0,1}([0-9]+))$');

    if (product_type != '') {
        document.getElementById('product-type-edit-' + id).style.borderColor = 'green';
        document.getElementById('type-mistake-' + id).classList.remove('is-visible');
    } else {
        document.getElementById('product-type-edit-' + id).style.borderColor = 'red';
        document.getElementById('type-mistake-' + id).classList.add('is-visible');
    }
    if (name_ru.length >= 2 && name_ru != '' && name_ruReg.test(name_ru)) {
        document.getElementById('name-ru-edit-' + id).style.borderColor = 'green';
        document.getElementById('nameRu-mistake-' + id).classList.remove('is-visible');
    } else {
        if (name_ru.length != 0) {
            document.getElementById('name-ru-edit-' + id).style.borderColor = 'red';
            document.getElementById('nameRu-mistake-' + id).classList.add('is-visible');
        }
    }
    if (name_en.length >= 2 && name_en != '' && name_enReg.test(name_en)) {
        document.getElementById('name-en-edit-' + id).style.borderColor = 'green';
        document.getElementById('nameEn-mistake-' + id).classList.remove('is-visible');
    } else {
        if (name_ru.length != 0) {
            document.getElementById('name-en-edit-' + id).style.borderColor = 'red';
            document.getElementById('nameEn-mistake-' + id).classList.add('is-visible');
        }
    }

    if (cost != '' && cost_valueReg.test(cost)) {
        document.getElementById('cost-edit-' + id).style.borderColor = 'green';
        document.getElementById('cost-mistake-' + id).classList.remove('is-visible');
    } else {
        if (cost.length != 0) {
            document.getElementById('cost-edit-' + id).style.borderColor = 'red';
            document.getElementById('cost-mistake-' + id).classList.add('is-visible');
        }
    }

    if (value != '' && cost_valueReg.test(value)) {
        document.getElementById('value-edit-' + id).style.borderColor = 'green';
        document.getElementById('value-mistake-' + id).classList.remove('is-visible');
    } else {
        if (value.length != 0) {
            document.getElementById('value-edit-' + id).style.borderColor = 'red';
            document.getElementById('value-mistake-' + id).classList.add('is-visible');
        }
    }

    if (image_name != "Choose file" && image_name != "\u0412\u044B\u0431\u0435\u0440\u0438\u0442\u0435 \u0444\u0430\u0439\u043B") {
        document.getElementById('image-name-edit-' + id).style.borderColor = 'green';
        document.getElementById('file-mistake-' + id).classList.remove('is-visible');
    } else {
        document.getElementById('image-name-edit-' + id).style.borderColor = 'red';
        document.getElementById('file-mistake-' + id).classList.add('is-visible');
    }
}

function checkEdit(id) {
    var name_en = document.getElementById("name-en-edit-" + id).value;
    var product_type = document.getElementById('product-type-edit-' + id).value;
    var name_ru = document.getElementById('name-ru-edit-' + id).value;
    var value = document.getElementById('value-edit-' + id).value;
    var cost = document.getElementById('cost-edit-' + id).value;
    var image_name = document.getElementById('image-name-edit-' + id).value;

    var name_ruReg = new RegExp('^([\u{0410}-\u{042F}]{1}[\u{0430}-\u{044F}\u{0410}-\u{042F}\\-\\s\\`]+)$');
    var name_enReg = new RegExp('^([A-Z]{1}[a-zA-Z-\\s\\-\\`]+)$');
    var cost_valueReg = new RegExp('^(([0-9]+)(\\.){0,1}([0-9]+))$');

    if (product_type != '' && name_ru.length >= 2 && name_ru != '' && name_ruReg.test(name_ru) &&
        name_en.length >= 2 && name_en != '' && name_enReg.test(name_en) && cost != '' && cost_valueReg.test(cost) &&
        value != '' && cost_valueReg.test(value) && image_name != "Choose file" &&
        image_name != "\u0412\u044B\u0431\u0435\u0440\u0438\u0442\u0435 \u0444\u0430\u0439\u043B") {
        return true;
    }
    return false;
}

function checkStaffPassword() {
    var password = document.getElementById("staff-new-password").value;
    var passwordReg = new RegExp('[a-zA-Z-_0-9]{6,}');
    if (passwordReg.test(password)){
        document.getElementById('staff-new-password-span').classList.remove('is-visible');
        return true;
    }else{
        document.getElementById('staff-new-password-span').classList.add('is-visible');
        return false;
    }

}