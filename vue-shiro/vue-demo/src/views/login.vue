<template>
<div class="bodyArea">
    <div class="stars"></div>
    <div class="loginPart">
        <div class="loginLogo">
            <img src="../../static/panda.svg" />
            <embed src="../../static/panda_re.svg" />
        </div>
        <logincomp></logincomp>
    </div>
</div>
</template>

<script>
import logincomp from "../components/login/login_comp.vue"
export default {
    data() {
        return {

        };
    },
    components: {
        logincomp
    },
    mounted() {
        this.initStars();
    },
    created() {

    },
    methods: {
        initStars() {
            var stars = 800; /*星星的密集程度，数字越大越多*/
            var $stars = $(".stars");
            var r = 800; /*星星的看起来的距离,值越大越远,可自行调制到自己满意的样子*/
            for (var i = 0; i < stars; i++) {
                var $star = $("<div/>").addClass("star");
                $stars.append($star);
            }
            $(".star").each(function () {
                var cur = $(this);
                var s = 0.2 + (Math.random() * 1);
                var curR = r + (Math.random() * 300);
                cur.css({
                    transformOrigin: "0 0 " + curR + "px",
                    transform: " translate3d(0,0,-" + curR + "px) rotateY(" + (Math.random() *
                            360) + "deg) rotateX(" + (Math.random() * -50) + "deg) scale(" + s + "," +
                        s + ")"
                })
            })
        },
    },

};
</script>

<style scoped>
.loginPart {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
}

.loginLogo {
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: center;
    padding: 20px 0;
}

.loginLogo>img,
.loginLogo>embed {
    margin: 0;
    height: 100px;
}
</style>
<style>
.bodyArea {
    height: 100%;
    background: radial-gradient(200% 100% at bottom center,
            #f7f7b6,
            #e96f92,
            #75517d,
            #1b2947);
    background: radial-gradient(220% 105% at top center,
            #1b2947 10%,
            #75517d 40%,
            #e96f92 65%,
            #f7f7b6);
    background-attachment: fixed;
    overflow: hidden;
    position: relative;
}

.stars {
    transform: perspective(500px);
    transform-style: preserve-3d;
    position: absolute;
    bottom: 0;
    perspective-origin: 50% 100%;
    left: 50%;
    animation: rotate 90s infinite linear;
}

.star {
    width: 2px;
    height: 2px;
    background: #f7f7b6;
    position: absolute;
    top: 0;
    left: 0;
    transform-origin: 0 0 -300px;
    transform: translate3d(0, 0, -300px);
    backface-visibility: hidden;
}



@keyframes rotate {
    0% {
        transform: perspective(400px) rotateZ(20deg) rotateX(-40deg) rotateY(0);
    }

    100% {
        transform: perspective(400px) rotateZ(20deg) rotateX(-40deg) rotateY(-360deg);
    }
}
</style>
