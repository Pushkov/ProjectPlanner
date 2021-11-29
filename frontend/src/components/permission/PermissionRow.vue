<template>
    <tr @click="itemInModal">
        <td>
            {{permission.name}}
        </td>
        <td>
            {{permission.comment}}
        </td>
    </tr>
</template>

<script>

    export default {
        name: "PermissionRow",
        props: {
            permission: {
                type: Object,
                default: () => {
                    return {}
                }
            },
        },
        components: {},
        methods: {
            itemInModal() {
                this.$emit('showModal', this.permission);
            }
        },

    }
</script>

<style scoped>

</style>