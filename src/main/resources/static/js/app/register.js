$().ready(function () {
    //유저 이름의 중복을 체크하는 함수 실행
    $("#duplicate-check").on("click", () => {
        ajaxCheckDup();
    })
    //Github id의 중복을 체크하는 함수 실행
    $("#github-id-check").on("click", () => {
        ajaxCheckIdValid();
    })
    // sign form
    const signInForm = document.getElementById("signin-form");
    signInForm.addEventListener("submit", (event) => {
        event.preventDefault();
        const ajax_form_data = new FormData(signInForm);
        let email = $("#personal-email").val() + "@" + $("#email-domain").val();
        ajax_form_data.append("email", email);
        ajax_form_data.delete("personal-email");
        ajax_form_data.delete("personal-email-domain");
        ajax_form_data.delete("password-check");
        // 이메일 데이터 병합

        $.ajax({
            type: "POST",
            url: "/api/v1/register/save",
            data: ajax_form_data,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',

            success: function (data) {
                console.log(data);
                if (data) {
                    alert('회원가입이 완료되었습니다! 로그인 해주세요.')
                    window.location.href = "/account/login";
                } else {
                    alert("회원가입에 실패했습니다.");
                }
            },
            error: function (data) {
                console.log(data);
                alert('Error Occurred');
            }
        });

    });
});

function setEmailDomain(id, email_domain) {
    if (email_domain === "") return;
    console.log("setEmailDomain", id, email_domain)
    let target_id = id.split("-select")[0]
    $(`#${target_id}`).val(email_domain)
}

function checkPassword() {
    let passwd = $("#password").val();
    let checked = $("#password-check").val();
    let feedback = $("#password-check-sign");
    if (checked.length > 0) {
        if (passwd === checked) {
            feedback.text("비밀번호가 일치합니다.")
            feedback.attr("class", "text-primary text-weak form-label");
        } else {
            feedback.text("비밀번호가 일치하지 않습니다.")
            feedback.attr("class", "text-danger text-weak form-label");
        }
    } else {
        feedback.text("비밀번호를 다시 입력해주세요.")
        feedback.attr("class", "text-weak form-label");
    }
}

function ajaxCheckDup() {
    console.log("ajaxCheckDup")
    let ajax_form_data = new FormData();
    let username = $('#username').val();
    let feedbackBtn = $('#duplicate-check');
    ajax_form_data.append('username', username);
    feedbackBtn.html(`<div class="spinner-border spinner-border-sm text-dark" role="status"></div>`);
    $.ajax({
        type: "GET",
        url: "/api/v1/register/check-username/" + username,
        dataType: 'json',
        processData: false,
        contentType: false,

        success: function (data) {
            console.log("return check username ", data);
            if (data) {
                alert('사용가능한 유저이름입니다.')
                feedbackBtn.css('backgroundColor', '#0D6EFD');
                feedbackBtn.css('color', '#FFFFFF');
            } else {
                alert("이미 존재하는 유저이름입니다.");
                feedbackBtn.css('backgroundColor', '#DC3545');
                feedbackBtn.css('color', '#FFFFFF');
            }
            feedbackBtn.html("Check");
        },
        error: function (data) {
            console.log("data", data);
            alert('이미 존재하는 유저이름입니다.');
            feedbackBtn.css('backgroundColor', '#DC3545');
            feedbackBtn.css('color', '#FFFFFF');
            feedbackBtn.html("Check");
        }
    });
}
