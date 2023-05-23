//ver y no ver contraseña de formularios
const passwordEye = document.querySelectorAll(".password");

passwordEye.forEach(element=> {
    element.addEventListener("click", togglePassword);
});

function togglePassword() {
    const icon = this.querySelector("i");

    if (this.nextElementSibling.type === "password") {
        this.nextElementSibling.type = "text";
        icon.classList.remove("fa-eye-slash");
        icon.classList.add("fa-eye");

    } else {
        this.nextElementSibling.type = "password";
        icon.classList.remove("fa-eye");
        icon.classList.add("fa-eye-slash");
    };
}

// Botón desplegable usuario logeado admin
function myFunction() {
    var dropdown = document.getElementById("myDropdown");
    if (dropdown.style.display === "none") {
      dropdown.style.display = "block";
    } else {
      dropdown.style.display = "none";
    }
  }
  
  window.onclick = function(event) {
    if (!event.target.matches('.dropbtn')) {
      var dropdowns = document.getElementsByClassName("dropdown-content");
      var i;
      for (i = 0; i < dropdowns.length; i++) {
        var openDropdown = dropdowns[i];
        if (openDropdown.style.display === "block") {
          openDropdown.style.display = "none";
        }
      }
    }
  }
  
  