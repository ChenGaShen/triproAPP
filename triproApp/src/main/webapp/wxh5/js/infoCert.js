(function() {

    window.LUploader = function(el) {


        var _self = this;
        _self.trigger = el;
        _self.init();
    };
    LUploader.prototype.init = function() {
        var _self = this;
        // _self.trigger.setAttribute('accept', _self.params.accept);
        // _self.params.multiple && _self.trigger.setAttribute('multiple', '');

        var btn = document.querySelector('#' + _self.trigger.getAttribute('data-LUploader'));


        var box = btn.childNodes[1];
        var uploaderList = box.childNodes[3];

        // console.log("jfdkljfkla");
        // console.log(btn);
        // console.log(btn.childNodes[1]);
        // console.log(box.childNodes[3]);


        btn.addEventListener('click', function() {
            _self.trigger.click();
        });
        _self.trigger.addEventListener('change', function() {
            var file = Array.prototype.slice.call(this.files)[0];

            var LUploaderList = _self.trigger.parentElement.querySelector('.LUploader-list');

            //判断是否支持FileReader
            if (window.FileReader) {
                var reader = new FileReader();
            } else {
                alert("您的设备不支持图片预览功能，如需该功能请升级您的设备！");
            }

            //获取文件
            var imageType = /^image\//;
            //是否是图片
            if (!imageType.test(file.type)) {
                alert("请选择图片！");
                return;
            }
            //读取完成
            reader.onload = function(e) {
                //图片路径设置为读取的图片
                LUploaderList.src = e.target.result;
            };
            reader.readAsDataURL(file);


        });
    };

})();


// (function() {
//
//     window.LUploader = function(el) {
//         var _self = this;
//         _self.trigger = el;
//         _self.init();
//     };
//     LUploader.prototype.init = function() {
//         var _self = this;
//         // _self.trigger.setAttribute('accept', _self.params.accept);
//         // _self.params.multiple && _self.trigger.setAttribute('multiple', '');
//
//         var btn = document.querySelector('#' + _self.trigger.getAttribute('data-LUploader'));
//         btn.addEventListener('click', function() {
//             _self.trigger.click();
//         });
//         _self.trigger.addEventListener('change', function() {
//             console.log(_self);
//
//         });
//     };
//
// })();