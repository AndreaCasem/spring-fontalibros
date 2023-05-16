// Subir imagenes formulario vender libro
window.addEventListener("load", () => {
    const inputImages = document.getElementById("imagenes");
    const containerFile = document.getElementById("container-file");

    //Cuando se seleccione un archivo, la función extraera el nombre y la extension del archivo,
    //despues se llamara a la funcion fileshow para mostrar el nombre y tipo de archivo al usuario
    inputImages.addEventListener("change", (e) => {
        let fileName = e.target.files[0].name;
        // console.log(fileName);
        let fileType = e.target.value.split(".").pop();
        // console.log(fileType);
        fileshow(fileName, fileType);
    })

    const fileshow = (fileName, fileType) => {
        const showFileBoxElem= document.createElement("div");
        showFileBoxElem.classList.add("show-file-box");
        const leftElem = document.createElement("div");
        leftElem.classList.add("left-file-box");
        const fileTypeElem = document.createElement("span");
        fileTypeElem.classList.add("file-type");
        fileTypeElem.innerHTML = fileType;
        leftElem.append(fileTypeElem);
        const fileTitleElem = document.createElement("p");
        fileTitleElem.innerHTML = fileName;
        leftElem.append(fileTitleElem);
        showFileBoxElem.append(leftElem);
        const rightFileBoxElem = document.createElement("div");
        rightFileBoxElem.classList.add("right-file-box");
        showFileBoxElem.append(rightFileBoxElem);
        const crossElem = document.createElement("span");
        crossElem.innerHTML = "&#215;";
        rightFileBoxElem.append(crossElem);
        containerFile.append(showFileBoxElem);
        //Eliminar archivos subidos
        crossElem.addEventListener("click", () => {
            containerFile.removeChild(showFileBoxElem);
        })
    }  
})