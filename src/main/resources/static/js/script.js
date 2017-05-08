$(document).ready(function() {
    var $window = $(window);
    var $screens = $('.screens'),
        height_prev = 0,
        height_max;
    $screens.find('img').each(function(idx) {
        var $this = $(this),
            height = $this.height(),
            width = $this.width();
        height_max = height_prev > height ? height_prev : height;
        switch (idx) {
            case 0:
                $this.css('left', 0);
                break;
            case 1:
                $this.css('right', 0);
                break;
            case 2:
                $this.css({
                    'left': '50%',
                    'margin-left': -(width / 2)
                });
                break
        }
    });
    $screens.find('p').height(height_max);
    var $carusel = $('#carousel-full, #carousel-anim'),
        animItemSlider = function() {
            var $this = $(this);
            animate.itemsInside($this).each(function() {
                var $this = $(this);
                animate.start($this)
            });
            $this.find('.item.active img').addClass('active')
        },
        animHideItemSlider = function() {
            var $this = $(this);
            animate.itemsInside($this).each(function() {
                var $this = $(this);
                animate.remove($this);
                animate.itemsHide($carusel)
            });
            $this.find('.item img').removeClass('active')
        };
    $carusel.on('slid.bs.carousel', animItemSlider);
    $carusel.on('slide.bs.carousel', animHideItemSlider);
    $carusel.each(animItemSlider);
    $carusel.find('.item.active img').addClass('active');
    var $charts = $('.chart'),
        $firstChart = $('.chart').first(),
        bPieChartWasSeeing = false,
        pieChart = function() {
            $charts.easyPieChart({
                barColor: '#7FCF4E',
                trackColor: '#DAE4F7',
                scaleColor: 'transparent',
                lineCap: 'butt',
                lineWidth: 16,
                size: 185,
                animate: 1500,
                onStep: function(from, to, percent) {
                    $(this.el).find('.percent').text(Math.round(percent))
                }
            });
            bPieChartWasSeeing = true
        };
    if ($charts.length) {
        if ($window.height() + $window.scrollTop() - $firstChart.offset().top > 0) {
            pieChart()
        } else {
            $window.scroll(function() {
                if (!bPieChartWasSeeing && $window.height() + $window.scrollTop() - $firstChart.offset().top > 0) {
                    pieChart()
                }
            })
        }
    }
    var $createStageToggle = $('#create-stage-toggle');
    $createStageToggle.find('a').click(function(e) {
        var $this = $(this);
        e.preventDefault();
        $createStageToggle.children().removeClass('active');
        $this.parent().addClass('active');
        $this.tab('show')
    });
    var $faces = $('.testimonial .face'),
        $quotes = $('.testimonial .quote'),
        aFaces = [],
        iFacesNum, iMaxFaces = 42;
    $faces.find('a').append('<span />');
    $faces.find('a').each(function() {
        aFaces.push('<a href="' + this.href + '">' + $(this).html() + '</a>')
    });
    iFacesNum = aFaces.length;
    if (iFacesNum < iMaxFaces) {
        for (var i = iFacesNum; i < iMaxFaces; i += 1) {
            $faces.append(aFaces[Math.floor(Math.random() * iFacesNum)])
        }
    }
    $faces.find('a').click(function(e) {
        var $this = $(this);
        e.preventDefault();
        $faces.find('a').removeClass('active');
        $this.addClass('active');
        $this.tab('show')
    });
    $faces.find('a:eq(20)').click();
    var $video = $('.video-prev');
    $video.append('<i class="fa fa-play" />');
    $video.append('<span class="overlay" />');
    $('.video-play-here').click(function() {
        $(this).html($('<iframe />', {
            src: this.href
        }));
        return false
    });
    $('time.cute-time').cuteTime();
    $('.our-team .btn').popover({
        animation: true,
        trigger: 'hover',
        delay: {
            show: 500,
            hide: 0
        }
    });
    var $selectCountry = $('#fieldSelectCountry'),
        $selectCity = $('#fieldSelectCity');
    if ($selectCountry.length && $selectCity.length) {
        $selectCity.find('option').prop('selected', false).hide();
        $selectCity.find('option.' + $selectCountry.val()).show();
        $selectCountry.change(function() {
            $selectCity.find('option').prop('selected', false).hide();
            $selectCity.find('option.' + this.value).show()
        })
    }
    if ($('form .btn-social').length) {
        $('form .input-social').hide();
        $('form .btn-social').click(function() {
            var $this = $(this),
                $fieldInputSocialURL;
            if ($this.hasClass('social-facebook')) {
                $fieldInputSocialURL = $('#fieldInputFacebook')
            } else if ($this.hasClass('social-youtube')) {
                $fieldInputSocialURL = $('#fieldInputYoutube')
            } else if ($this.hasClass('social-twitter')) {
                $fieldInputSocialURL = $('#fieldInputTwitter')
            } else if ($this.hasClass('social-gplus')) {
                $fieldInputSocialURL = $('#fieldInputGplus')
            } else if ($this.hasClass('social-dribbble')) {
                $fieldInputSocialURL = $('#fieldInputDribbble')
            } else if ($this.hasClass('social-linkedin')) {
                $fieldInputSocialURL = $('#fieldInputLinkedin')
            } else if ($this.hasClass('social-tumblr')) {
                $fieldInputSocialURL = $('#fieldInputTumblr')
            }
            if ($this.hasClass('btn-gray')) {
                $this.removeClass('btn-gray');
                $fieldInputSocialURL.fadeIn().focus()
            } else {
                $this.addClass('btn-gray');
                $fieldInputSocialURL.val('').fadeOut()
            }
            return false
        })
    }
    $('form .form-curtain').click(function() {
        $(this).fadeOut();
        return false
    });
    $('form.search-blog .submit').click(function() {
        var $form = $(this).parent(),
            $inputText = $form.find('input[role="search"]');
        if ($inputText.val() != '') {
            $form.submit();
            $form.find('i.fa').attr('class', 'fa fa-spinner fa-spin')
        }
        return false
    });
    $('#loadCategoryMore').click(function() {
        var $button = $(this);
        $('.category-box-row-hidden').slideDown('slow', function() {
            $button.fadeOut()
        });
        return false
    });
    var $prettyPhoto = $('a[rel^="prettyPhoto"]');
    if ($prettyPhoto.length) {
        $prettyPhoto.prettyPhoto({
            animation_speed: 'fast',
            slideshow: 5000,
            autoplay_slideshow: false,
            opacity: 0.80,
            show_title: true,
            allow_resize: true,
            default_width: 500,
            default_height: 344,
            counter_separator_label: '/',
            theme: 'pp_default',
            horizontal_padding: 20,
            hideflash: false,
            wmode: 'opaque',
            autoplay: true,
            modal: false,
            deeplinking: true,
            overlay_gallery: true,
            keyboard_shortcuts: true,
            changepicturecallback: function() {},
            callback: function() {},
            ie6_fallback: true,
            custom_markup: '',
            social_tools: false
        })
    }
});