$(document).ready(function () {

    // Navigation
    const menu = new Menu(".menu", ".nav", ".contents", ["Home", "Experience", "Projects", "Resume", "Contact"])
    menu.setListener();

    // Function(s)
    function setListenerPathname(element, pathname) {
        $(element).click(function (){
            window.location.pathname = pathname;
            return false;
        });
    }

    // Initialize lets talk button
    const letsTalkBtn = new AnimateButton("#lets-talk", ".sub", "body");
    setListenerPathname("#lets-talk", "/contact");

    // Listener
    $(window).scroll(function () {
        const marcinText = $(".nav .title");
        const marcinImg = $('#marcin');
        const menuBtn = $('.menu');

        if ((marcinImg.offset().top+marcinImg.outerHeight(true))-(menuBtn.offset().top+menuBtn.outerHeight(true)) > 0) {
            if (menuBtn.hasClass('blue')) {
                menuBtn.toggleClass('blue');
                letsTalkBtn.moveButtonBack();
                setListenerPathname("#lets-talk", "/contact");
                marcinText.toggleClass('invisible');
                menu.invertColor();
            }
        } else {
            if (!menuBtn.hasClass('blue')) {
                menuBtn.toggleClass('blue');
                letsTalkBtn.moveButtonTo();
                setListenerPathname("#lets-talk", "/contact");

                marcinText.toggleClass('invisible');
                menu.invertColor();
            }
        }
    });

    // On-load check pathname, then redirect user by scrolling to section
    const experienceSection = new Section(".experience");
    const projectsSection = new Section(".projects");

    if(location.pathname === "/experience"){
        experienceSection.scrollToSection();
    } else if (location.pathname === "/projects"){
        projectsSection.scrollToSection();
    }
    if(location.pathname.includes("home")){
        history.pushState(null, "/home", '/');
    }

    // Credit to, Moe Amaya. Rellax (Parallax): https://github.com/dixonandmoe/rellax
    var rellax = new Rellax('.rellax', {
        speed: -2,
        center: false,
        wrapper: null,
        round: true,
        vertical: true,
        horizontal: false
    });
})