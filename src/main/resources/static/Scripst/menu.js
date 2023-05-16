const menu = document.querySelector("#menu");
const btnMenu = document.querySelector("#btn-menu");

btnMenu.addEventListener("click", function(e){
    // Si el elemento clickeado es un submenu-btn, no desaparecer el menú
    if (e.target.classList.contains("submenu-btn")) {
        e.preventDefault();
        return;
    }

    menu.classList.toggle("mostrar");
});

// cerrar menu
const btnClose = document.querySelector("#btn-close");
btnClose.addEventListener("click", function(){
    menu.classList.remove("mostrar");
});

// // Utilizando tabs para el menu
// const targets = document.querySelectorAll('[data-target]')
// const content = document.querySelectorAll('[data-content]')

// targets.forEach(target => {
// 	target.addEventListener('click', () => {
// 		content.forEach(quitar => {
// 			quitar.classList.remove('active');
// 		})
// 		const traer = document.querySelector(target.dataset.target)
// 		traer.classList.add('active');
//         //
//         window.scrollTo(0, target.offsetTop);
// 	})
// })

// Desplegar submenu
const subMenuBtn = document.querySelectorAll(".submenu-btn");
for (let i = 0; i <  subMenuBtn.length; i++) {
    subMenuBtn[i].addEventListener("click", function(){
        if (window.innerWidth < 950) {
            const subMenu = this.nextElementSibling;
            const height = subMenu.scrollHeight;

            if (subMenu.classList.contains("desplegar")) {
                subMenu.classList.remove("desplegar");
                subMenu.removeAttribute("style");
            } else {
                subMenu.classList.add("desplegar");
                subMenu.style.height = height + "px";
            }
        }
    })
}

//Desaparecer menú al dar clic en un item que no es submenu-btn
const menuLinks = document.querySelectorAll('.menu a[a^=""]:not(.submenu-btn');
    menuLinks.forEach((menuLink) => {
        menuLink.addEventListener("click", function () {
            menu.classList.remove("mostrar");
        });
    })

// Cuando se recargue la página se iniciará en la página home
// document.getElementById("home").click();
