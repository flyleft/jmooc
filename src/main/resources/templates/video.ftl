<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>video</title>
    <link href="//vjs.zencdn.net/5.11/video-js.min.css" rel="stylesheet">
    <link href="/css/video.css" rel="stylesheet">
</head>
<body>
<video
        id="my-player"
        class="vjs-matrix video-js"
        controls
        poster="/img/city.jpg"
>
    <source src="${video!}" type="video/mp4"/>
</video>
<script src="//vjs.zencdn.net/5.11/video.min.js"></script>
<script type="text/javascript">
    var options = {};
    var player = videojs('my-player', options, function onPlayerReady() {
        this.play();
        this.on('ended', function() {

        });
    });
</script>
</body>
</html>