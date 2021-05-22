class Menu {
    constructor(button, parentRoot, parentList, pathNamesList) {
        this.button = button;
        this.parentRoot = parentRoot;
        this.parentList = parentList;
        this.listElement = this.getListElement(pathNamesList);
    }

    invertColor() {
        $(".line").each( (i, v) => {
            $(v).toggleClass("line-blue");
        });
    }

    setListener() {
        $(this.button).click( () => {
            $(this.button).toggleClass("opened");

            setTimeout( () => {
                let parentRoot = $(this.parentRoot)

                if ($(this.button).hasClass("opened")) {
                    console.log("Yes")
                    parentRoot.find(this.parentList).append(this.listElement);
                    parentRoot.css("z-index", 5);
                } else {
                    console.log("No")
                    parentRoot.removeAttr("style");
                    parentRoot.find(this.parentList).find(".list").remove();
                }
            }, 50);
        });
    }

    getListElement(pathNamesList) {

        let divList = document.createElement("div");
        let unorderedList = document.createElement("ul");

        $(pathNamesList).each( (i) => {

            let currentPathname = window.location.pathname;
            if (currentPathname === "/" && pathNamesList[i] === "Home") {return;}
            if (!currentPathname.includes(pathNamesList[i].toLowerCase())) {
                let anchorTag = document.createElement("a");
                let listItem = document.createElement("li");
                anchorTag.innerText = pathNamesList[i];
                anchorTag.setAttribute("id", pathNamesList[i].toLowerCase());
                anchorTag.setAttribute("href", "https://marcinkoziel.ca/"+pathNamesList[i].toLowerCase());
                listItem.appendChild(anchorTag);
                unorderedList.appendChild(listItem);
            }
        });

        divList.setAttribute("class", "list")
        divList.appendChild(unorderedList);

        return divList;
    }

}