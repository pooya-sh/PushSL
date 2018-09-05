$(document).ready(() => {
    $("#btnSearch").click(searchTrip);
    $("#date").val(getTodayDate());
    $("#time").val(getNowTime());
}
);

function getTodayDate() {
    let today = new Date();
    let dd = today.getDate();
    if (dd < 10) {
        dd = '0' + dd;
    }
    let mm = today.getMonth() + 1;
    if (mm < 10) {
        mm = '0' + mm;
    }
    let yyyy = today.getFullYear();
    return yyyy + '-' + mm + '-' + dd;
}

function getNowTime() {
    let today = new Date();
    let hh = today.getHours();
    if (hh < 10) {
        hh = '0' + hh;
    }
    let MM = today.getMinutes();
    if (MM < 10) {
        MM = '0' + MM;
    }
    return hh + ':' + MM;
}

function getOrigins() {
    $("#originList").empty();
    let textData = $("#inputOrigin").val();
    fetch('http://localhost:8080/siteinfo', {
        method: 'post',
        headers: {
            'Content-Type': 'application/json'
        },
        body: textData
    }).then(function (response) {
        return response.json();
    }).then(function (data) {
        console.log(data)
        for (let key in data) {
            let option = document.createElement("OPTION");
            // option.setAttribute("name", data[key].SiteId);
            option.setAttribute("value", data[key].Name);
            option.classList.add("form-control")

            $("#originList").append(option);
            console.log(option);
        }
    });
}

function getDests() {
    $("#destList").empty();
    let textData = $("#inputDestination").val();
    fetch('http://localhost:8080/siteinfo', {
        method: 'post',
        headers: {
            'Content-Type': 'application/json'
        },
        body: textData
    }).then(function (response) {
        return response.json();
    }).then(function (data) {
        console.log(data)
        for (let key in data) {
            let option = document.createElement("OPTION");
            // option.setAttribute("name", data[key].SiteId);
            option.setAttribute("value", data[key].Name);
            option.classList.add("form-control")

            $("#destList").append(option);
            console.log(option);
        }
    });
}

function searchTrip() {
    let origin = $("#inputOrigin").val();
    let destination = $("#inputDestination").val();
    let date = $("#date").val();
    let time = $("#time").val();

    console.log(date);
    console.log(new Date());

    let obj = {
        originId: origin,
        destId: destination,
        date: date,
        time: time
    };

    let str = 'originName=' + origin + '&destName=' + destination + '&date=' + date + '&time=' + time;

    fetch('http://localhost:8080/search', {
        method: 'post',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: str
    }).then(function (response) {
        return response.json();
    }).then(function (data) {
        parseTrip(data);
        // renderTrip();
    });
}


function parseTrip(output) {

    for (let row in output) {

        let trip = output[row];

        let resultDiv = document.createElement("DIV");
        resultDiv.classList.add("py-2", "my-3");
        resultDiv.setAttribute("id", "resultDiv");

        let tripDiv = document.createElement("DIV");
        tripDiv.setAttribute("data-toggle", "collapse");
        tripDiv.setAttribute("href", "#detail" + row);
        tripDiv.innerHTML = '<p><span>'+ trip.startTime +'</span> <i class="fas fa-long-arrow-alt-right"></i> <span>'+ trip.endTime +'</span></p>' +
                    '<p>Restid: <span>'+ trip.totalTravelTime +'</span></p>'+
                    '<p><span>'+ trip.originName +'</span> <i class="fas fa-long-arrow-alt-right"></i> <span>'+ trip.destName +'</span></p>'
                    ;
        tripDiv.classList.add("m-4", "text-white");

        let list = trip.legList;

        for(let detail in list){
            let leg = list[detail];
            let o = leg.Origin;
            let d = leg.Destination;

            let detailDiv = document.createElement("DIV");
            detailDiv.setAttribute("id", "detail" + row);
            detailDiv.classList.add("collapse");
            detailDiv.innerHTML = '<p><span>'+ o.time +'</span> <span>'+ o.name +'</span></p>' +
                                '<p><span>Tunnelbananas blåa linje....etc</span></p>' +
                                '<p><span>'+ d.time +'</span> <span>'+ d.name +'</span></p>'
                                ;
            tripDiv.appendChild(detailDiv);
        }

        resultDiv.appendChild(tripDiv);
        $("#secondContainer").append(resultDiv);
        // console.log(data[row].endTime);
        // console.log(data[row].legList);
        // let l = data[row].legList;
        // for(let leg in l){
        //     console.log(l[leg].Destination);
        // }
    }
}