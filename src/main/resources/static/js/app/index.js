const main = {
    init: function () {
        let _this = this;
        $('#btn-save').on('click', () => {
            _this.save();
        });
        $('#btn-update').on('click', () => {
            _this.update();
        });
        $('#btn-delete').on('click', () => {
            _this.delete();
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
    }
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
    $('.sidebar-link').each(function() {
        console.log("$(this).attr('href')", $(this).attr('href'));
        console.log("window.location.pathname", window.location.pathname);

        if($(this).attr('href') === window.location.pathname) {
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
        }
        else {
            $(this).addClass('bi-chevron-down');
            $(this).removeClass('bi-chevron-up');
            $(target).addClass('none');
        }
    });
});