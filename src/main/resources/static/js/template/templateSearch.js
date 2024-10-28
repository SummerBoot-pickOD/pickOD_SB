document.addEventListener('DOMContentLoaded', function() {
    const refButton = document.querySelector('.content-reference');
    refButton.classList.add('bold');

    function handleButtonClick(event) {
        document.querySelectorAll('input[type="button"]').forEach(button => {
            button.classList.remove('bold');
        });
        event.target.classList.add('bold');
    }

    const buttons = document.querySelectorAll('input[type="button"]');
    buttons.forEach(button => {
        button.addEventListener('click', handleButtonClick);
    });

    $(function () {
        $("#header").load("../main/header.html");
        });
    
        $(function () {
        $("#footer").load("../main/footer.html");
        });
});





