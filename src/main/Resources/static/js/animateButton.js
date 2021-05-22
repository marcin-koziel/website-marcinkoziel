class AnimateButton {
    constructor(elementId, fromElement, toElement) {
        this.elementId = elementId;
        this.fromElement = fromElement;
        this.toElement = toElement;
        this.initialPosition = new Position(elementId);
        this.window = $(window);
    }

    moveButton(elementId, toElement) {
        let letsTalkBtnPos = new Position(elementId);

        let letsTalkBtnElement = letsTalkBtnPos.getElement();
        let letsTalkBtnPadding = letsTalkBtnElement.css('padding').replace('px', '');

        letsTalkBtnElement.remove();

        let insertDiv = document.createElement('div');
        let innerTag = document.createElement('a')
        insertDiv.setAttribute('id', elementId.replace('#', ''));
        innerTag.innerText = "Let\'s Talk";
        insertDiv.appendChild(innerTag);

        $(toElement).prepend(insertDiv);

        console.log(`top: ${letsTalkBtnPos.top} - right: ${letsTalkBtnPos.right} - bottom: ${letsTalkBtnPos.bottom} - left: ${letsTalkBtnPos.left}`);
        // console.log(`top: ${this.initialPosition.top} - right: ${this.initialPosition.right} - bottom: ${this.initialPosition.bottom} - left: ${this.initialPosition.left}`);

        $(elementId)
            .css('position', 'fixed')
            .css('padding', '20px')
            .css('top', letsTalkBtnPos.top * this.window.height())
            .css('right', letsTalkBtnPos.right * this.window.width())
            .css('bottom', letsTalkBtnPos.bottom * this.window.height())
            .css('left', letsTalkBtnPos.left * this.window.width());

        if (toElement === this.toElement) {
            console.log(" -- From -- ")

            $(elementId)
                .toggleClass('fixed');

            setTimeout(function () {
                $(elementId)
                    .removeAttr('style')
            })

        } else {
            console.log(" -- To -- ")

            $(elementId)
                .css("position", "fixed")
                .css("transition", "all 100ms ease")
                .css("top", (((1-this.initialPosition.bottom)*this.window.height())-this.initialPosition.height) - this.window.scrollTop())
                .css("bottom", (this.initialPosition.bottom*this.window.height()) + this.window.scrollTop())
                .css('left', (this.window.width()/2) - letsTalkBtnPos.width)

        }
        setTimeout(function () {
            $(elementId)
                // .removeAttr('style')
                .css('width', letsTalkBtnPos.width+(letsTalkBtnPadding*2))
                .css('height', letsTalkBtnPos.height+(letsTalkBtnPadding*2))

        }, 30)
        setTimeout(function (){
            $(elementId)
                .toggleClass("lets-animate")
                .removeAttr('style')
        }, 100)
    }

    moveButtonTo() {
        this.moveButton(this.elementId, this.toElement)
    };

    moveButtonBack() {
        this.moveButton(this.elementId, this.fromElement)
    };

}