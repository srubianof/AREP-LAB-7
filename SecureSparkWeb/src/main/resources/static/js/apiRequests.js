apiRequests = (function () {
    return {
        sendUserPasswd: function (username, password) {
            console.log(username + " " + password)
            axios.post('/login', {username: username, password: password}).then(
                res => {
                    console.log(res.data)
                    if (res.data === "si") {
                        console.log("chihcihci")
                        location.reload();
                    }
                }
            );
        }

    }
})();
