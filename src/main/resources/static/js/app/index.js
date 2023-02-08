const main = {
    init: function () {
        let _this = this;
        $('#btn-login').on('click', () => {
            _this.login();
        });
        $('#btn-save').on('click', () => {
            _this.save();
        });
        $('#btn-update').on('click', () => {
            _this.update();
        });
        $('#btn-delete').on('click', () => {
            _this.delete();
        });
        $('#btn-team-save').on('click', () => {
            _this.saveTeamImage();
        });
        $('#btn-team-update').on('click', () => {
            _this.updateTeamImage();
        });
        $('#btn-team-apply').on('click', () => {
            _this.applyTeam();
        });
    },
    login: function () {
        let data = {
            username: $('#username').val(),
            password: $('#password').val(),
        };
        $.ajax({
            type: 'POST',
            url: '/account/login',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function (data) {
            console.log(data['msg']);
            alert(data['msg']);
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    save: function () {
        let data = {
            title: $('#title').val(),
            author: $('#author').val(),
            content: $('#content').val(),
            boardId: parseInt($('#selectBoard').val()),
            userId: parseInt($('#user').val()),
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/posts',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('글이 등록되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    update: function () {
        let data = {
            title: $('#title').val(),
            content: $("#content").val(),
            boardId: parseInt($('#selectBoard').val()),
        };
        let id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/v1/posts/' + id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('글이 수정되었습니다.');
            window.location.href = '/';
        }).fail()(function (error) {
            alert(JSON.stringify(error));
        });
    },
    delete: function () {
        let id = $('#id').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/posts/' + id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8'
        }).done(function () {
            alert('글이 삭제되었습니다.');
            window.location.href = '/';
        }).fail()(function (error) {
            alert(JSON.stringify(error));
        });
    },
    saveTeam: function (img) {
        let data = {
            name: $('#name').val(),
            owner: $('#owner').val(),
            description: $('#description').val(),
            userId: parseInt($('#user').val()),
            picture: img
        };
        $.ajax({
            type: 'POST',
            url: '/api/v1/teams',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function (data) {
            console.log("data", data);
            alert('팀이 생성되었습니다.');
            window.location.href = '/teams';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    saveTeamImage: function () {
        let inputPicture = $('#picture')[0];
        if (inputPicture.files.length > 0) {
            let formData = new FormData();
            formData.append('uploadImage', inputPicture.files[0]);
            $.ajax({
                type: 'POST',
                url: '/api/v1/images',
                processData: false,
                contentType: false,
                data: formData
            }).done(function (data) {
                main.saveTeam(data);
            }).fail(function (error) {
                console.log(error);
                alert("사진 저장에 실패했습니다.");
                main.saveTeam(null);
            });
        } else {
            main.saveTeam(null);
        }
    },
    updateTeam: function (img) {
        let data = {
            name: $('#name').val(),
            owner: $('#owner').val(),
            description: $('#description').val(),
            userId: parseInt($('#user').val()),
        };
        let id = $('#id').val();
        if (img != null) {
            data["picture"] = img
        }
        $.ajax({
            type: 'PUT',
            url: '/api/v1/teams/' + id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function (data) {
            console.log("data", data);
            alert('팀 정보를 업데이트했습니다.');
            window.location.href = '/teams';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    updateTeamImage: function () {
        let inputPicture = $('#picture')[0];
        if (inputPicture.files.length > 0) {
            let formData = new FormData();
            formData.append('uploadImage', inputPicture.files[0]);
            $.ajax({
                type: 'POST',
                url: '/api/v1/images',
                processData: false,
                contentType: false,
                data: formData
            }).done(function (data) {
                main.updateTeam(data);
            }).fail(function (error) {
                console.log(error);
                alert("사진 저장에 실패했습니다.");
                main.updateTeam(null);
            });
        } else {
            main.updateTeam(null);
        }
    },
    applyTeam: function () {
        console.log("apply Team");
        let data = {
            content: $('#content').val(),
            userId: parseInt($('#user').val()),
            teamId: parseInt($('#team').val()),
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/user-team-relations',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('팀에 지원했습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

};

main.init();
$().ready(function () {
    let target = $("#selectBoard").data("target");
    $(`option[value='${target}']`).attr("selected", "selected");
    new SlimSelect({
        select: '#selectBoard',
        settings: {
            showSearch: false,
            placeholderText: '게시판',
        }
    });
    // 현재 주소를 체크해서 사이드바의 아이템의 폰트 색을 변경
    $('.sidebar-link').each(function () {
        console.log("$(this).attr('href')", $(this).attr('href'));
        console.log("window.location.pathname", window.location.pathname);

        if ($(this).attr('href') === window.location.pathname) {
            $(this).parent().addClass('selected');
        }
    });
    // 아이템 접기/펴기
    $('.folder').click(function () {
        let target = $(this).data('fold-target');
        if ($(target).hasClass("none")) {
            $(this).addClass('bi-chevron-up');
            $(this).removeClass('bi-chevron-down');
            $(target).removeClass('none');
        } else {
            $(this).addClass('bi-chevron-down');
            $(this).removeClass('bi-chevron-up');
            $(target).addClass('none');
        }
    });
    // id 카드 다운로드
    const cssDecl = getComputedStyle(document.documentElement);
    $("#btn-save-id-card").on("click", ()=>{
        const screenshotTarget = document.getElementById("id-card");
        const idCard = document.getElementById("profile-id-card");
        idCard.style.position = "relative";
        idCard.style.zIndex = "1057";
        let shadow_ele = Array(6);
        for(let i=0; i<6; i++){
            shadow_ele[i] = document.createElement("div");
            shadow_ele[i].style.display = "block";
            shadow_ele[i].style.width = idCard.getBoundingClientRect().width+2-i + "px";
            shadow_ele[i].style.height = idCard.getBoundingClientRect().height-i + "px";
            shadow_ele[i].style.backgroundColor = '#0000000d';
            shadow_ele[i].style.position = "absolute";
            shadow_ele[i].style.borderRadius = "15px";
            shadow_ele[i].style.top = "16px";
            shadow_ele[i].style.left = 4+0.5*i + "px";
            shadow_ele[i].style.zIndex = "1056";
            screenshotTarget.appendChild(shadow_ele[i]);
        }

        html2canvas(screenshotTarget,
            {scale:2, backgroundColor: cssDecl.getPropertyValue('--developer-bg-color')})
            .then((canvas)=>{
            canvas.getContext('2d').filter = 'blur(4px)';
            const base64image = canvas.toDataURL("image/png");
            let anchor = document.createElement('a');
            anchor.setAttribute("href", base64image);
            anchor.setAttribute("download", "my-image.png");
            anchor.click();
            anchor.remove();
        });
        idCard.removeAttribute("style");
        shadow_ele.forEach((shadow)=>{
            shadow.remove();
        });
    });
});