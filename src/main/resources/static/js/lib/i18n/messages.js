/*
 * 官方文档：https://kazupon.github.io/vue-i18n/zh/started.html
 */
// 准备翻译的语言环境信息
const messages = {
    "en_US": {
        message: {
            translate: 'Translations',
            generate: 'Generate',
            genrating: 'Generating...',
            copySuccess: 'Copy Success',
            copyError: 'Copy Failed',
            notSupportIE: 'IE browser does not support copy function',
            clickCopySrc: 'Click to copy source code',
            clearCache: 'Clear Cache',
            clearSuccess: 'Clearing cache succeeded'
        }
    },
    "zh_CN": {
        message: {
            translate: '翻译',
            generate: '一键生成',
            genrating: '生成中...',
            copySuccess: '复制成功',
            copyError: '复制失败',
            notSupportIE: 'IE浏览器不支持复制功能',
            clickCopySrc: '点击复制源码',
            clearCache: '清除缓存',
            clearSuccess: '清除缓存成功'
        }
    }
}

// 通过选项创建 VueI18n 实例
const i18n = new VueI18n({
    locale: 'zh_CN', // 设置地区（语言环境）
    fallbackLocale: 'zh_CN', // 当前语言环境没有要获取的值时，默认从这个语言环境查找（预设的语言环境·首选语言缺少翻译时要使用的语言）
    messages, // 设置地区信息（本地化的语言环境信息）
    silentFallbackWarn: true, // 仅在根本没有可用的转换时生成警告
    preserveDirectiveContent: true // 解决翻译内容闪烁的问题(有过渡动画时，可复现此问题)
})