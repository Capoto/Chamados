$(document).ready(function () {

    function initCustomSelect(selector) {
        const wrapper = document.querySelector(selector);
        if (!wrapper) return;

        const display = wrapper.querySelector(".bs-select-display");
        const text = wrapper.querySelector(".selectText");
        const valueInput = wrapper.querySelector(".selectValue");
        const options = wrapper.querySelectorAll(".bs-option");

        // abrir/fechar dropdown
        display.addEventListener("click", (e) => {
            e.stopPropagation(); // evita conflito com o click fora
            closeAllSelects(wrapper); // fecha outros selects
            wrapper.classList.toggle("show");
        });

        // selecionar opção
        options.forEach(opt => {
            opt.addEventListener("click", (e) => {
                e.stopPropagation();
                const title = opt.querySelector(".opt-title").innerText;
                text.innerText = title;
                valueInput.value = opt.dataset.value;
                wrapper.classList.remove("show");
            });
        });
    }

    // fecha todos os outros selects ao abrir um novo
    function closeAllSelects(except = null) {
        document.querySelectorAll(".custom-select").forEach(sel => {
            if (sel !== except) sel.classList.remove("show");
        });
    }

    // fechar ao clicar fora
    document.addEventListener("click", function () {
        closeAllSelects();
    });

    // inicializar selects
    initCustomSelect("#select1");
    initCustomSelect("#select2");
});