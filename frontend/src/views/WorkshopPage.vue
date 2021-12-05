<template>
    <div>
        <NavigationBar/>
        <h1>Workshop page</h1>
        {{ $t("message.hello") }}
    </div>
</template>

<script>
    import NavigationBar from "@/components/nav/NavigationBar";

    export default {
        name: "Workshop",
        components: {
            NavigationBar
        },
        methods: {
            test() {
                this.$i18n.locale = 'ru';
            }
        },
        mounted() {
            // this.test();
        }
    }
</script>

<style scoped>

</style>