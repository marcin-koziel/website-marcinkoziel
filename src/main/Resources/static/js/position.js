class Position {
    constructor(id) {
        this.setPositionId(id);
    }

    setPositionId(id) {
        this.window = $(window)
        this.element = $(id);
        const padding = this.element.css('padding').replace('px', '');

        this.width = this.element.width();
        this.height = this.element.height();
        this.top = this.element.offset().top - this.window.scrollTop();
        this.left = this.element.offset().left - this.window.scrollLeft();
        this.right = this.window.width() - (this.left + (this.width+(padding*2)));
        this.bottom = this.window.height() - (this.top+ (this.height+(padding*2)));

        this.top = this.top / this.window.height();
        this.left = this.left / this.window.width();
        this.right = this.right / this.window.width();
        this.bottom = this.bottom / this.window.height();

        // console.log(`top: ${this.top} - right: ${this.right} - bottom: ${this.bottom} - left: ${this.left}`);
        // console.log(`Height: ${this.window.height()} - ScrollTop: ${this.window.scrollTop()}`);
        // console.log(`Test: ${$('body').height()}`);
        // console.log(this.window.scrollTop() / $('body').height() * $('body').height() + (0.048 * this.window.height()));

    }

    getElement() {
        return this.element;
    }
}