/*   
Template Name: eLearn Multi-Purpose HTML Template - Learning made easy
Version: 1.0
Author: KL-Webmedia
*/

$(document).ready(function () {
    
    // GLOBAL - optimization
    var $window = $(window);
    
    /* =========================
    Header show screen (Home 1)
    ========================= */
    var $screens = $('.screens'), height_prev=0, height_max;
    $screens.find('img').each(function (idx) {
        var $this = $(this), height = $this.height(), width = $this.width();
        height_max = height_prev > height ? height_prev : height; // search heighter img
        switch(idx) {
            case 0: $this.css('left', 0); break;
            case 1: $this.css('right', 0); break;
            case 2: $this.css({'left':'50%', 'margin-left':-(width/2)}); break;
        }
    });
    $screens.find('p').height(height_max); // set heightest img to contaner P
    
    
    /* ===========================================================
    Slider (Carousel, Home 2&3) - full width slide + anim slide up
    =========================================================== */
    var $carusel = $('#carousel-full, #carousel-anim'),
        animItemSlider = function () {
            var $this = $(this);
            animate.itemsInside($this).each(function () {
                var $this = $(this);
                animate.start($this);
            });
            $this.find('.item.active img').addClass('active');
        },
        animHideItemSlider = function () {
            var $this = $(this);
            animate.itemsInside($this).each(function () {
                var $this = $(this);
                animate.remove($this);
                animate.itemsHide($carusel); 
                //animItemHide();
            });
            $this.find('.item img').removeClass('active');
        }; 
    $carusel.on('slid.bs.carousel', animItemSlider); // event: end of sliding
    $carusel.on('slide.bs.carousel', animHideItemSlider); // event: start of sliding
    $carusel.each(animItemSlider); // start, before slider events
    $carusel.find('.item.active img').addClass('active'); // anim Img (Home 3)
    
    /* carousel - configuration, optional
    $carusel.carousel({
        interval: 5000, // The amount of time to delay between automatically cycling an item. If false, carousel will not automatically cycle.
        pause: 'hover', // Pauses the cycling of the carousel on mouseenter and resumes the cycling of the carousel on mouseleave.
        wrap: true      // Whether the carousel should cycle continuously or have hard stops.
    });
    */
    

    /* ===================
    Plugin: Easy pie chart
    ====================== */
    var $charts = $('.chart'), 
        $firstChart = $('.chart').first(), 
        bPieChartWasSeeing = false, 
        pieChart = function() { 
            $charts.easyPieChart({
                barColor: '#7FCF4E', // The color of the curcular bar. You can pass either a css valid color string like rgb, rgba hex or string colors. But you can also pass a function that accepts the current percentage as a value to return a dynamically generated color.
                trackColor: '#DAE4F7', // The color of the track for the bar, false to disable rendering.
                scaleColor: 'transparent', // The color of the scale lines, false to disable rendering.
                lineCap: 'butt', // Defines how the ending of the bar line looks like. Possible values are: butt, round and square.
                lineWidth: 16, // Width of the bar line in px.
                size: 185, // Size of the pie chart in px. It will always be a square.
                animate: 1500, // Time in milliseconds for a eased animation of the bar growing, or false to deactivate.
                //onStart: $.noop, // Callback function that is called at the start of any animation (only if animate is not false).
                //onStop: $.noop, // Callback function that is called at the end of any animation (only if animate is not false).
                //easing: 'easeOutBounce',
                onStep: function(from, to, percent) {
                    $(this.el).find('.percent').text(Math.round(percent));
                }
            });
            bPieChartWasSeeing = true; // user have seen Pie Chart
            //pieChart = function () {}; // function pieChart() will be start only 1 times !
        };
    if($charts.length) { // if Chart is on site
        if($window.height() + $window.scrollTop() - $firstChart.offset().top > 0) { // if Pie Chart is on screen or above - start animation ...
            pieChart();
        } else {
            $window.scroll(function() { // ... else monit event Scroll
                if(!bPieChartWasSeeing  &&  $window.height() + $window.scrollTop() - $firstChart.offset().top > 0) {
                    pieChart();
                }
            });
        }
    }
    
    
    /* ============================================
    Bootstrap: Togglable tabs - Profil Create Stage
    ============================================ */
    var $createStageToggle = $('#create-stage-toggle');
    $createStageToggle.find('a').click(function (e) {
        var $this = $(this);
        e.preventDefault();
        $createStageToggle.children().removeClass('active'); // remove last active
        $this.parent().addClass('active'); // set new active link
        $this.tab('show'); // show tab
    });
    

    /* ====================================
    Bootstrap: Togglable tabs - Testimonial
    ==================================== */
    var $faces = $('.testimonial .face'),  $quotes = $('.testimonial .quote'), aFaces=[], iFacesNum, iMaxFaces=42;
    $faces.find('a').append('<span />'); /* add color filter */
    $faces.find('a').each(function () {
        aFaces.push('<a href="'+this.href+'">' + $(this).html() + '</a>');
        
    });
    // testimonial container must have 42 faces, 
    // if we put less the rest will be filled with the same randomly chosen
    iFacesNum = aFaces.length;
    if(iFacesNum < iMaxFaces) {
        for(var i=iFacesNum; i<iMaxFaces; i+=1) {
            $faces.append(aFaces[Math.floor(Math.random()*iFacesNum)]); // random face
        }
    }
    $faces.find('a').click(function(e) { /* event Click and change quote */
        var $this = $(this);
        e.preventDefault();
        $faces.find('a').removeClass('active'); // remove last active
        $this.addClass('active'); // set new active link
        $this.tab('show'); // show tab
    });
    
    $faces.find('a:eq(20)').click(); // check middle face


    /* ==========================
    Video - set player
    ========================== */
    var $video = $('.video-prev');
    $video.append('<i class="fa fa-play" />'); /* play sign */
    $video.append('<span class="overlay" />'); /* overlay */
    $('.video-play-here').click(function () { /* player load in the same place */
        $(this).html($('<iframe />', {
            src: this.href
        }));
        return false;
    });
    
    
    /* ==========================
    Plugin: cuteTime
    ========================== */    
    $('time.cute-time').cuteTime();
    
    
    /* ==========================
    Bootstrap: popover (tooltip)
    ========================== */    
    $('.our-team .btn').popover({
        animation: true, // apply a CSS fade transition to the tooltip
        trigger: 'hover', // how popover is triggered - click | hover | focus | manual
        delay: {show: 500, hide: 0} // delay showing and hiding the popover (ms) - does not apply to manual trigger type ...
        //more - http://getbootstrap.com/javascript/#popovers
    });


    /* ===========================
    Select depend - form register
    =========================== */
    var $selectCountry = $('#fieldSelectCountry'), $selectCity = $('#fieldSelectCity');
    if($selectCountry.length && $selectCity.length) { // if script find the form with select country
        $selectCity.find('option').prop('selected', false).hide();
        $selectCity.find('option.' + $selectCountry.val()).show();
        $selectCountry.change(function () {
            $selectCity.find('option').prop('selected', false).hide();
            $selectCity.find('option.' + this.value).show();
        });
    }


    /* =============================
    Social URL Input - form register
    ============================= */    
    if($('form .btn-social').length) {
        $('form .input-social').hide();
        $('form .btn-social').click(function () {
            var $this = $(this), $fieldInputSocialURL;
            if($this.hasClass('social-facebook')) {
                $fieldInputSocialURL = $('#fieldInputFacebook');
            }
            else if($this.hasClass('social-youtube')) {
                $fieldInputSocialURL = $('#fieldInputYoutube');
            }
            else if($this.hasClass('social-twitter')) {
                $fieldInputSocialURL = $('#fieldInputTwitter');
            }
            else if($this.hasClass('social-gplus')) {
                $fieldInputSocialURL = $('#fieldInputGplus');
            }
            else if($this.hasClass('social-dribbble')) {
                $fieldInputSocialURL = $('#fieldInputDribbble');
            }
            else if($this.hasClass('social-linkedin')) {
                $fieldInputSocialURL = $('#fieldInputLinkedin');
            }
            else if($this.hasClass('social-tumblr')) {
                $fieldInputSocialURL = $('#fieldInputTumblr');
            }

            if($this.hasClass('btn-gray')) {
                $this.removeClass('btn-gray');
                $fieldInputSocialURL.fadeIn().focus();
            }
            else {
                $this.addClass('btn-gray');
                $fieldInputSocialURL.val('').fadeOut();
            }
            return false;
        });
    }

    
    /* =============================
    Curtain - form application 
    ============================= */ 
    $('form .form-curtain').click(function () {
        $(this).fadeOut();
        return false;
    });

    
    /* =============================
    Submit link - form search blog 
    ============================= */ 
    $('form.search-blog .submit').click(function () {
        var $form = $(this).parent(), $inputText = $form.find('input[type="search"]');
        if($inputText.val() != '') {
            $form.submit();
            $form.find('i.fa').attr('class', 'fa fa-spinner fa-spin'); /* loader */
        }
        return false;
    });

    
    /* =============================
    Category boxes - button More
    ============================= */
    $('#loadCategoryMore').click(function () {
        var $button = $(this);
        $('.category-box-row-hidden').slideDown('slow', function () {
            $button.fadeOut();
        });
        return false;
    });


    /* =============================
    Plugin: prettyPhoto (zoom image)
    ============================= */
    var $prettyPhoto = $('a[rel^="prettyPhoto"]');
    if($prettyPhoto.length) {
        $prettyPhoto.prettyPhoto({
            animation_speed: 'fast', /* fast/slow/normal */
            slideshow: 5000, /* false OR interval time in ms */
            autoplay_slideshow: false, /* true/false */
            opacity: 0.80, /* Value between 0 and 1 */
            show_title: true, /* true/false */
            allow_resize: true, /* Resize the photos bigger than viewport. true/false */
            default_width: 500,
            default_height: 344,
            counter_separator_label: '/', /* The separator for the gallery counter 1 "of" 2 */
            theme: 'pp_default', /* light_rounded / dark_rounded / light_square / dark_square / facebook */
            horizontal_padding: 20, /* The padding on each side of the picture */
            hideflash: false, /* Hides all the flash object on a page, set to TRUE if flash appears over prettyPhoto */
            wmode: 'opaque', /* Set the flash wmode attribute */
            autoplay: true, /* Automatically start videos: True/False */
            modal: false, /* If set to true, only the close button will close the window */
            deeplinking: true, /* Allow prettyPhoto to update the url to enable deeplinking. */
            overlay_gallery: true, /* If set to true, a gallery will overlay the fullscreen image on mouse over */
            keyboard_shortcuts: true, /* Set to false if you open forms inside prettyPhoto */
            changepicturecallback: function(){}, /* Called everytime an item is shown/changed */
            callback: function(){}, /* Called when prettyPhoto is closed */
            ie6_fallback: true,
            //markup: '<div class="pp_pic_holder">...</div><div class="pp_overlay"></div>',
            //gallery_markup: '<div class="pp_gallery">...</div>',
            //image_markup: '...',
            //flash_markup: '...',
            //quicktime_markup: '...',
            //iframe_markup: '...',
            //inline_markup: '...',
            custom_markup: '',
            social_tools: false /* html or false to disable */
        });
    }
});