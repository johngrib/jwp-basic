// $(".qna-comment").on("click", ".answerWrite input[type=submit]", addAnswer);
$(".answerWrite input[type=submit]").click(addAnswer);
$(".link-delete-article").click(deleteAnswer);

function addAnswer(e) {
  e.preventDefault();

  var queryString = $("form[name=answer]").serialize();

  $.ajax({
    type : 'post',
    url : '/api/qna/addAnswer',
    data : queryString,
    dataType : 'json',
    error: onError,
    success : onSuccess,
  });
}

function onSuccess(json, status){
  var answer = json.answer;
  var answerTemplate = $("#answerTemplate").html();
  var template = answerTemplate.format(answer.answerId, answer.writer, new Date(answer.createdDate), answer.contents, answer.answerId, answer.answerId, json.questionId);
  $(".qna-comment-slipp-articles").prepend(template)
      .find(".link-delete-article")
      .click(deleteAnswer);
  $("#answerCount").text(json.answerCount);
}

function onError(xhr, status) {
  alert("error");
}

function deleteAnswer(e) {
  e.preventDefault();
  var queryString = $(this).closest(".form-delete").serialize();
  $.ajax({
      type : 'post',
      url : '/api/qna/deleteAnswer',
      data : queryString,
      dataType : 'json',
      error: onError,
      success : onDeleteSuccess,
  });
}

function onDeleteSuccess(json, status) {
  if(json.result && json.result.status) {
    $("#answer_" + json.answerId).remove();
    $("#answerCount").text(json.answerCount);
  }
}

String.prototype.format = function() {
  var args = arguments;
  return this.replace(/{(\d+)}/g, function(match, number) {
    return typeof args[number] != 'undefined'
        ? args[number]
        : match
        ;
  });
};