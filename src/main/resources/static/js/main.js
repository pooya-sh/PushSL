$(document).ready(() => {
    $("#btnSearch").click(sendSearchForm);
    $("#date").val(getTodayDate());
    $("#time").val(getNowTime());
    $("#reminderCancel").click(cancelReminder);
    $("#reminderOK").click(startReminder);
    $("#btnOpenEmailForm").click(openEmailForm);
}
);

let tripLocalArray = [];
let rtCheckInterval = 0;
let upCounterInterval = 0;

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
    let today = addTime(new Date(), 5);
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
        for (let key in data) {
            let option = document.createElement("OPTION");
            option.setAttribute("value", data[key].Name);
            option.classList.add("form-control")

            $("#originList").append(option);
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
        for (let key in data) {
            let option = document.createElement("OPTION");
            option.setAttribute("value", data[key].Name);
            option.classList.add("form-control")

            $("#destList").append(option);
        }
    });
}

function searchTrip() {
    $("#listContainer").empty();
    let origin = $("#inputOrigin").val();
    let destination = $("#inputDestination").val();
    let date = $("#date").val();
    let time = $("#time").val();

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

function validateSearchForm() {
    if ($("#inputOrigin").val() === "" ||
        $("#inputDestination").val() === "" ||
        $("#date").val() === "" ||
        $("#time").val() === "") {
        return false;
    } else {
        return true;
    }
}

function sendSearchForm() {
    if (validateSearchForm()) {
        searchTrip();
    } else {
        console.log('Form not valid');
    }
}


function parseTrip(output) {

    for (let row in output) {

        let trip = output[row];

        let travelTime = '';

        if (trip.hours === "") {
            travelTime = trip.minutes + 'min';
        } else {
            travelTime = trip.hours + ' h  ' + trip.minutes + ' min';
        }

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
            '<button class="btn btn-link btn-reminder reminderButton" value="' + row + '" type="button" id="reminderBtn' + row + '"><i class="fas fa-bell"></i></button>' +
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
                '<p>&emsp;&emsp;<span>' + leg.name + '</span> mot <span>' + leg.direction + '</span></p>' +
                '<p>&emsp;<i class="fas fa-angle-double-right"></i> <span>' + endTime + '</span> <span>' + d.name + '</span></p>' +
                '</div>'
                ;
            tripDiv.appendChild(detailDiv);
        }

        resultDiv.appendChild(tripDiv);
        $("#listContainer").append(resultDiv);
        $("#reminderBtn" + row).click(showReminderForm);
    }
}

function showReminderForm() {
    $("#reminderFormBg").removeClass('invisible');
    $("#reminderFormBg").addClass('visible');
    $("#tripIndex").val($(this).val());

    let chosenTrip = tripLocalArray[$('#tripIndex').val()];
    let expectedDep = parsedTimeTableTime(chosenTrip.startTime);
    rtCheckInterval = setInterval(() => {
        fetch('http://localhost:8080/checktime', {
            method: 'post',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(chosenTrip)
        }).then(function (response) {
            return response.text();
        }).then(function (data) {
            expectedDep = parseTime(data);
        });
    }, 5000);
    upCounterInterval = setInterval(() => {
        updateCounter(new Date(), expectedDep);
    }, 1000);
}

function cancelReminder() {
    clearInterval(rtCheckInterval);
    clearInterval(upCounterInterval);

    $("#btnOpenEmailForm").removeClass("myInvisible");
    $("#emailForm").addClass("myInvisible");
    $("#reminderOK").addClass("myInvisible");

    $("#reminderFormBg").removeClass('visible');
    $("#reminderFormBg").addClass('invisible');
    $("#counter").text('00:00');
    $("#counterInfo").addClass('myInvisible');
    $("#btnOpenEmailForm").prop('disabled', false);

}

function startReminder() {
    let chosenTrip = tripLocalArray[$('#tripIndex').val()];
    let email = $("#inputReminderEmail").val();
    let reminderMinutes = $("#inputReminderMinutes").val();
    chosenTrip.email = email;
    chosenTrip.reminderMinutes = reminderMinutes;
    // let expectedDep = parsedTimeTableTime(chosenTrip.startTime);
    fetch('http://localhost:8080/reminder', {
        method: 'post',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(chosenTrip)
    }).then(function (response) {
        return response.json();
    }).then(function (data) {
        if (data) {
            console.log('Reminder set');
        } else {
            console.log('Could not set reminder');
        }

    });

}

function updateCounter(now, departure) {
    console.log('cnow: ' + now);
    console.log('cdep: ' + departure);
    let diff = departure - now;
    console.log('timediff ' + diff)
    if (diff < 0) {
        $("#counter").text('00:00');
        $("#counterInfo").removeClass('myInvisible');
        $("#btnOpenEmailForm").prop('disabled', true);
    } else {
        $("#counterInfo").addClass('myInvisible');
        $("#btnOpenEmailForm").prop('disabled', false);
        let counterMinutes = Math.floor(diff / 60000);
        let counterSeconds = Math.floor((diff % 60000) / 1000);
        if (counterSeconds < 10) {
            counterSeconds = '0' + counterSeconds;
        }
        if (counterMinutes < 10) {
            counterMinutes = '0' + counterMinutes;
        }
        $("#counter").text(counterMinutes + ':' + counterSeconds);
    }
}

function addTime(date, min) {
    return new Date(date.getTime() + min * 60000);
}

function parseTime(dateStr) {
    let year = dateStr.substring(0, 4);
    let month = Number(dateStr.substring(5, 7)) - 1;
    let day = dateStr.substring(8, 10);
    let h = dateStr.substring(11, 13);
    let m = dateStr.substring(14, 16);
    let s = dateStr.substring(17, 19);
    let parsedDate = new Date(year, month, day, h, m, s);
    console.log('parsed: ' + parsedDate);
    return parsedDate;
}

function parsedTimeTableTime(timeTableTime) {
    let h = timeTableTime.substring(0, 2);
    let m = timeTableTime.substring(3, 5);
    let s = timeTableTime.substring(6);
    let timeTableDate = new Date();
    timeTableDate.setHours(h, m, s);
    return timeTableDate;

}

function openEmailForm() {
    $("#btnOpenEmailForm").addClass("myInvisible");
    $("#emailForm").removeClass("myInvisible");
    $("#reminderOK").removeClass("myInvisible");
}