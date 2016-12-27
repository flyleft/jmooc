/*!
 * Animation 
 * based on "animate.css" [http://daneden.github.io/animate.css/]
 * 
 * @author Marcin Dobroszek <marcin.dobroszek@gmail.com>
 * @version 0.3.1
 */
 
var animate = {
    // List of all element from site to animate
    itemsAll: $('.animated'),
    // List of elements from other elements to animate
    itemsInside: function (parent) {
        return parent.find('.animated');
    },
    // Container with animation elem. - start after event scroll (screen position depend)
    containersScroll: $('.animation-scroll'),
    // Container with animation elem. - start after page is load
    containersDomready: $('.animation-domready'),
    // Start item animation
    start: function (item) {
        if (item.length == 1) {
            item.addClass(item.data('animation')); // set kind of anim - set CSS class
        } else if (item.length > 1) {
            item.each(function () {
                var $this = $(this);
                $this.addClass($this.data('animation'));
            });
        }
    },
    // Remove item animation
    remove: function (item) {
        item.removeClass(item.data('animation')); // remove kind of anim - remove CSS class
    },
    // Some kind of animation type require hide element before animation start
    itemsHide: function (parent) {
        (parent ? this.itemsInside(parent) : this.itemsAll).each(function () { // we need use $('.animated') , not this.itemsAll
            var $this = $(this);
            switch($this.data('animation')) {
                case 'fadeIn':
                    $this.css('opacity',0);
                    break;
                case 'fadeInUp':
                    $this.css('opacity',0);
                    break;
                /*
                case 'bounceInRight':
                    $this.css('-webkit-transform','translateX(2000px)');
                    $this.css(        'transform','translateX(2000px)');
                    break;
                */
            }            
        });
    },
    setDelay: function () {
        this.itemsAll.each(function () {
            var $item = $(this); 
            if($item.data('animation-delay')) {
                $item.css({
                    '-webkit-animation-delay': $item.data('animation-delay'),
                    '-moz-animation-delay'   : $item.data('animation-delay'),
                    '-ms-animation-delay'    : $item.data('animation-delay'),
                    'animation-delay'        : $item.data('animation-delay')
                });
            }
        });
    },
    setScrollMonit: function () {
        this.containersScroll.each(function () {
            var $container = $(this), $window = $(window);
            if($window.height() + $window.scrollTop() - $container.offset().top > 0) { // if container is on screen or above - no animation
                animate.start( animate.itemsInside($container) );
                
            } else {
                $window.scroll(function() { // ... else monit event Scroll
                    if($window.height() + $window.scrollTop() - $container.offset().top > 0) {
                        animate.start( animate.itemsInside($container) );
                    }
                });
            }
        });
    },
    setDomreadyMonit: function () {
        this.containersDomready.each(function () {
            var $container = $(this);
            animate.start( animate.itemsInside($container) );
        });
    },
    init: function () {
        this.setDelay();
        this.itemsHide();
        this.setDomreadyMonit();
        this.setScrollMonit();
    }
};

    
$(document).ready(function () {
    animate.init();
});