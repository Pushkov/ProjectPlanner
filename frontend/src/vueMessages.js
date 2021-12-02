const messages = {
    en: {
        message: {
            hello: 'hello world'
        }
    },
    ru: {
        message: {
            hello: 'Привет мир'
        }
    }
}

// Создание экземпляра VueI18n с настройками
const i18n = new VueI18n({
    locale: 'ru', // установка локализации по умолчанию
    messages // установка сообщений локализаций
})