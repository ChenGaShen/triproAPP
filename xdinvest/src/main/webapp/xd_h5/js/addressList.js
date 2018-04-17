function deleteAddress() {
    var confirmView = document.getElementsByClassName("dialog-wrap confirm-wrap")[0]
    confirmView.style = "opacity: 1;"
}

function btnCancel() {
    var confirmView = document.getElementsByClassName("dialog-wrap confirm-wrap")[0]
    confirmView.style = "opacity: 0; display: none;"
}

function btnConfirm() {
    var confirmView = document.getElementsByClassName("dialog-wrap confirm-wrap")[0]
    confirmView.style = "opacity: 0; display: none;"
}

// ----------------------------------------------
