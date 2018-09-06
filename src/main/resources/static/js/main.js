$(document).ready(() => {
    $("#btnSearch").click(searchTrip);
    $("#date").val(getTodayDate());
    $("#time").val(getNowTime());
    $("#reminderCancel").click(cancelReminder);
    $("#reminderOK").click(startReminder);
}
);

let tripLocalArray = [];


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
    $("#listContainer").empty();
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
        tripLocalArray = data;
        parseTrip(data);
    });
}


function parseTrip(output) {

    for (let row in output) {

        let trip = output[row];

        let travelTime = '';

        console.log('hour: ' + trip.minutes);

        if (trip.hours === "") {
            travelTime = trip.minutes + 'min';
        } else {
            travelTime = trip.hours + ' h  ' + trip.minutes + ' min';
        }

        // if (trip.totalTravelTime > 59) {
        //     let hours = Math.floor(trip.totalTravelTime / 60);
        //     let minutes = trip.totalTravelTime % 60;
        //     travelTime = hours + ' h  ' + minutes + ' min';
        // } else {
        //     travelTime = trip.totalTravelTime + ' min';
        // }

        let startTime = trip.startTime.substring(0, 5);
        let endTime = trip.endTime.substring(0, 5);


        let resultDiv = document.createElement("DIV");
        resultDiv.classList.add("py-2", "my-3");
        resultDiv.setAttribute("id", "resultDiv");
        resultDiv.setAttribute("data-toggle", "collapse");
        resultDiv.setAttribute("href", "#detail" + row);

        let tripDiv = document.createElement("DIV");
        tripDiv.innerHTML = '<div class="d-flex justify-content-between">' +
            '<p><span>' + startTime + '</span> <i class="fas fa-long-arrow-alt-right"></i> <span>' + endTime + '</span></p>' +
            '<p>Restid: <span>' + travelTime + '</span></p>' +
            '</div>' +
            '<div class="d-flex justify-content-between align-items-center">' +
            '<p><span>' + trip.originName + '</span> <i class="fas fa-long-arrow-alt-right"></i> <span>' + trip.destName + '</span></p>' +
            '<button class="btn btn-link btn-reminder reminderButton" value="' + row + '" type="button"><i class="fas fa-bell"></i></button>' +
            '</div>'
            ;
        tripDiv.classList.add("m-4");

        let list = trip.legList;

        for (let detail in list) {
            let leg = list[detail];
            let o = leg.Origin;
            let d = leg.Destination;

            let startTime = o.time.substring(0, 5);
            let endTime = d.time.substring(0, 5);

            let detailDiv = document.createElement("DIV");
            detailDiv.setAttribute("id", "detail" + row);
            detailDiv.classList.add("collapse");
            detailDiv.innerHTML = '<div id="detailDiv">' +
                '<div class="dropdown-divider"></div>' +
                '<p>&emsp;<i class="fas fa-angle-double-down"></i> <span>' + startTime + '</span> <span>' + o.name + '</span></p>' +
                '<p>&emsp;&emsp;<span>' + leg.category + '</span></p>' +
                '<p>&emsp;<i class="fas fa-angle-double-right"></i> <span>' + endTime + '</span> <span>' + d.name + '</span></p>' +
                '</div>'
                ;
            tripDiv.appendChild(detailDiv);
        }

        resultDiv.appendChild(tripDiv);
        $("#listContainer").append(resultDiv);
        $(".reminderButton").click(() => {
            $("#reminderFormBg").removeClass('invisible');
            $("#reminderFormBg").addClass('visible');
            $("#tripIndex").val(this.value);
            console.log($("#tripIndex").val());
            console.log(tripLocalArray[$("#tripIndex").val()]);
        });
    }
}

function showReminderForm() {
    $("#reminderFormBg").removeClass('invisible');
    $("#reminderFormBg").addClass('visible');
    $("#tripIndex").val($(this).val());
    console.log($("#tripIndex").val());
    console.log(tripLocalArray[$("#tripIndex").val()]);
}

function cancelReminder() {
    $("#reminderFormBg").removeClass('visible');
    $("#reminderFormBg").addClass('invisible');
}

function startReminder() {


}