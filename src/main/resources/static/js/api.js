//GET
function getPost() {

    //글도 안쓰고 글 조회 눌렀을때 에러 발생하는거
    //글이나 쓰고 조회해라고 알려주기 위한 꼼수 메소드 -> global.js에 있는 메소드 참조
    if (!validate()) {
        return;
    }

    //http://localhost:8080/posts/1 ->1번글을 보기위해서 보내여 하는주소
    //fetch('http://localhost:8080/posts/' + getSequence())
    fetch(`http://localhost:8080/posts/${getSequence()}`)
        .then(resp => resp.json())
        .then(data => {
            console.log(data);

            //global.js에 있는 메소드 참조
            refresh(data.title, data.author, data.contents);
            alert("가장 최신 글로 데이터 갱신! ");

        })
        .catch(err => {
            alert("에러발생!");
            console.log(err);
        })

}


//POST
function createPost() {

    //JSON
    //Content-type: application/json
    const data = getPostValues();

    fetch('http://localhost:8080/posts', {
        method: "POST", headers: {
            "Content-Type": "application/json"
        }, //자바에서는 다른언어의 객체
        body: JSON.stringify(data)
    })
        .then(resp => resp.json())
        .then(data => {
            console.log(data);
            updateSequence(data);
            alert("게시물등록완료!");
            clearPostBody();
        })
        .catch(err => console.log(err));

}


//fetch
function updatePost() {

    if( !validate() ){
        return;
    }

    const data = getPatchValues();

    fetch(`http://localhost:8080/posts/${getSequence()}`, {
        method: "PATCH",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(data)
    })
        .then(resp => resp.json())
        .then(data => {
            console.log(data);
            refresh(
                data.title,
                data.author,
                data.contents
            );
            updateSequence(data.id);
            alert("게시물 수정 완료!");
            clearPatchBody();

        })
        .catch(err => {
            alert("에러발생!");
            console.log(err);
        })

}

function removePost() {

    if(!validate()){
        return;
    }

    fetch(`http://localhost:8080/posts/${getSequence()}`, {
        method: "DELETE"
    })
        .then(resp => {
            console.log(resp);
            refresh('Title', 'Author', 'Content');
            syncSequence();
            alert("마지막 글이 삭제 되었습니다!");
        })
        .catch();

}
