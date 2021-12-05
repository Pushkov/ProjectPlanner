import Vue from 'vue'
import VueI18n from 'vue-i18n'
import messages from './lang'

Vue.use(VueI18n)
// const i18n = new VueI18n({
//     locale: this.$store.getters.GET_LOCALE,
//     messages
// })
// export default i18n

export default function makeI18n(locale) {
    return new VueI18n({
        locale,
        messages
    });
}


// locale: localStorage.lang || this.$store.getters.GET_LOCALE,