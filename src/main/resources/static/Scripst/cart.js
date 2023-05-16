const btnCartOpen = document.getElementById("btn-shopping-cart");
const btnCartClose = document.getElementById("btn-close-cart");
const containerCart = document.getElementById("container-cart");

//Mostrar carrito
btnCartOpen.addEventListener("click", () => {
    containerCart.style.display = "flex";
})

//Cerrar carrito
btnCartClose.addEventListener("click", () => {
    containerCart.style.display = "none";
});

//Importando y consumiendo datos de un json local para fines demostrativos
import data from "./productos.json" assert {type: "json"};
console.log(data);

let shoppingCartArray = [];
let total = 0;
let productContainer = document.querySelector(".last-book-container")
let totalElement = document.getElementById("total-price");

const productsArrays = data;

productsArrays.forEach(product => {
    // console.log(product);
    productContainer.innerHTML += `
    <div class="card" id="${product.id}">
        <figure class="img-book">
            <img src="https://picsum.photos/400/310" alt="libro">
        </figure>
        <h3 class="book-title" id="book-title">${product.titulo}</h3>
        <p id="author">${product.autor}</p>
        <p id="status">${product.estado}</p>
        <span class="span-word" id="price">${product.precio}</span>
        <button class="btn-add-to-cart btn" id="btn-add-to-car" data id>Añadir al carrito</button>
    </div>`
});

//Escuchando cuando se hace click en un boton de agregar al carrito
let addButton = document.querySelectorAll(".btn-add-to-cart");
// console.log(addButton);

//Transformando el NodeList a arreglo
addButton = [...addButton];
// console.log(addButton);

let listContainer = document.querySelector(".list-cart");

addButton.forEach((btn => {
    btn.addEventListener("click", e => {
        // console.log("click");
        //AGREGANDO PRODUCTOS AL CARRITO

        //Buscando el id del producto
        let actualId = parseInt(e.target.parentNode.id);
        console.log(actualId);
        
        //Con el id encontrar el objeto actual
        //El parametro item representa cada uno de los elementos del arreglo
        let actualProduct = productsArrays.find(item => item.id == actualId);
        console.log(actualProduct);
        //Agregando los productos al arreglo 
        shoppingCartArray.push(actualProduct);
        console.log(shoppingCartArray);

        //Agregando el producto al carrito
        listContainer.innerHTML += `
        <div class="product" id="product">
            <img src="https://picsum.photos/400/310" alt="book">
            <div class="text-cart">
                <p>${actualProduct.titulo}</p>
                <span class="span-word">${actualProduct.precio}</span>
            </div>
            <span class="delete-product"><i class="fa-solid fa-trash"></i></span>
        </div>`;

        //Guardar los productos en el almacenamiento
        localStorage.setItem("shoppingCartArray", JSON.stringify(shoppingCartArray));

        //Actualizando el valor total
        total = getTotal();
        // console.log(total);

        totalElement.innerText = `$${total}`;

       
    });
}))


function getTotal() {
    let sumTotal;
    let total = shoppingCartArray.reduce((sum, item) => {
        sumTotal = sum + item.precio;
        return sumTotal;
    }, 0);
    return total;
}


