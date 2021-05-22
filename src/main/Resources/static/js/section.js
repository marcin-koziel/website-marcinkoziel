class Section {
    constructor(selector) {
        this.selector = selector;
    }

    scrollToSection(){
        let element = $(this.selector)
        $([document.documentElement, document.body]).animate({
            scrollTop: element.offset().top - $(window).height() / 6
        }, 300);
        element.addClass("highlight").delay(1000).queue(function() {
            element.removeClass("highlight");
        });
        // window.location.href = "/"
        history.pushState(null, this.selector.replace(".", "").replace("#", ""), '/');
    }
}