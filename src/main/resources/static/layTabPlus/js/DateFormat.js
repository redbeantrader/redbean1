var DateFormat = {
    toDate: function (date) {
        if (date == null || date == "null") {
            return "";
        }
        var datetime = new Date(date);
        var year = datetime.getFullYear();
        var month = datetime.getMonth() + 1;
        month = month >= 10 ? month : ("0" + month);
        var date = datetime.getDate();
        date = date >= 10 ? date : ("0" + date);
        return year + "-" + month + "-" + date;
    }, toDatetime: function (date) {
        if (date == null || date == "null") {
            return "";
        }
        var datetime = new Date(date);
        var year = datetime.getFullYear();
        var month = datetime.getMonth() + 1;
        month = month >= 10 ? month : ("0" + month);
        var date = datetime.getDate();
        date = date >= 10 ? date : ("0" + date);
        var hour = datetime.getHours();
        hour = hour >= 10 ? hour : ("0" + hour);
        var minutes = datetime.getMinutes();
        minutes = minutes >= 10 ? minutes : ("0" + minutes);
        var seconds = datetime.getSeconds();
        seconds = seconds >= 10 ? seconds : ("0" + seconds);
        return year + "-" + month + "-" + date + " " + hour + ":" + minutes + ":" + seconds;
    }
}