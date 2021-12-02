<template>
    <div>
        <NavigationBar/>
        <h1>Workshop page</h1>
        <p>{{ $t("i18n.hello") }}</p>
    </div>
</template>

<script>
    import NavigationBar from "@/components/nav/NavigationBar";
    import i18n from "@/vueMessages"

    export default {
        name: "Workshop",
        components: {
            NavigationBar,
            i18n
        }
    }
</script>

<style scoped>

</style>