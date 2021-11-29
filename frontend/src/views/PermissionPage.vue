<template>
    <div>
        <NavigationBar/>
        <PermissionList
                :perm-list="PERMISSIONS"
        />
    </div>
</template>

<script>
    import {mapActions, mapGetters} from 'vuex'
    import NavigationBar from "@/components/nav/NavigationBar";
    import PermissionList from "../components/permission/PermissionList";

    export default {
        name: "Permission",
        data() {
            return {
                currentPermission: {},
                isModal: false,
                isModalEdit: false,
                isModalCreate: false,
            }
        },
        components: {
            PermissionList,
            NavigationBar
        },
        computed: {
            ...mapGetters([
                'PERMISSIONS'
            ])
        },
        methods: {
            ...mapActions([
                'GET_ALL_PERMISSIONS'
            ]),

        },
        mounted() {
            this.GET_ALL_PERMISSIONS();
        }
    }
</script>

<style scoped>

</style>