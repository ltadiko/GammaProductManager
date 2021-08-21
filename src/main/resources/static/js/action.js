var HttpClient = function () {
    this.get = function (aUrl, aCallback) {
        let anHttpRequest = new XMLHttpRequest();
        anHttpRequest.onreadystatechange = function () {
            if (anHttpRequest.readyState == 4 && anHttpRequest.status == 200)
                aCallback(anHttpRequest.responseText);
        }

        anHttpRequest.open("GET", aUrl, true);
        anHttpRequest.send(null);
    }
}


function checkAccess() {
    let username = document.getElementById("username").value;
    if (isEmpty(username)) {
        alert("Please enter username")
        return false;
    }

    let password = document.getElementById("password").value;
    if (isEmpty(password)) {
        alert("Please enter password")
        return false;
    }

    var http = new XMLHttpRequest();
      let tokenURL = "http://localhost:8080/oauth/token;
    var params = 'grant_type=password&username="+username+"&password="+password';
            http.setRequestHeader("Authorization", "Basic Y2xpZW50aWQ6Y2xpZW50LXNlY3JldA==");
            http.setRequestHeader('Content-type', 'application/json');
    http.open('POST', tokenURL, true);


    http.onreadystatechange = function() {//Call a function when the state changes.
        if(http.readyState == 4 && http.status == 200) {
            alert(http.responseText);
        }
    }

    http.send(params);
}

function addProduct() {
    let username = document.getElementById("username").value;
    if (isEmpty(username)) {
        alert("Please enter username")
        return false;
    }

    let password = document.getElementById("password").value;
    if (isEmpty(password)) {
        alert("Please enter password")
        return false;
    }

    const client = new HttpClient();
    let getStoresURL = "http://localhost:8080/stores";

    client.get(getStoresURL, function (response) {

        let stores = JSON.parse(response);
        let tableRef = document.getElementById('storeTable').getElementsByTagName('tbody')[0];
        tableRef.innerHTML = '';
        for (let i = 0; i < stores.length; i++) {
            let newRow = tableRef.insertRow();
            let currentStore = stores[i];
            addCell(newRow, 0, currentStore.storeId);
            addCell(newRow, 1, currentStore.city);
            addCell(newRow, 2, currentStore.postalCode);
            addCell(newRow, 3, currentStore.street);
            addCell(newRow, 4, currentStore.address_number);
            addCell(newRow, 5, currentStore.todayOpen);
            addCell(newRow, 6, currentStore.collectionPoint);
            addCell(newRow, 7, currentStore.todayClose);
        }
    });
}

function getStores() {

    const client = new HttpClient();
    let getStoresURL = "http://localhost:8080/stores";

    client.get(getStoresURL, function (response) {

        let stores = JSON.parse(response);
        let tableRef = document.getElementById('storeTable').getElementsByTagName('tbody')[0];
        tableRef.innerHTML = '';
        for (let i = 0; i < stores.length; i++) {
            let newRow = tableRef.insertRow();
            let currentStore = stores[i];
            addCell(newRow, 0, currentStore.storeId);
            addCell(newRow, 1, currentStore.city);
            addCell(newRow, 2, currentStore.postalCode);
            addCell(newRow, 3, currentStore.street);
            addCell(newRow, 4, currentStore.address_number);
            addCell(newRow, 5, currentStore.todayOpen);
            addCell(newRow, 6, currentStore.collectionPoint);
            addCell(newRow, 7, currentStore.todayClose);
        }
    });
}

function addCell(row, index, value) {
    var cell = row.insertCell(index);
    if (value) {
        cell.appendChild(document.createTextNode(value));
    } else {
        cell.appendChild(document.createTextNode(""));
    }
}

function isEmpty(val) {
    return (val === undefined || val == null || val.length <= 0) ? true : false;
}
