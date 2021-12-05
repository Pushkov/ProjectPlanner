<template>
    <tr @click="itemSelect">
        <td>{{ item.id }}</td>
        <td>{{ item.name }}</td>
        <td>{{ item.fullName }}</td>
        <td>{{ item.index }}</td>
    </tr>
</template>

<script>
    export default {
        name: "WorkshopTableRow",
        props: {
            item: {
                type: Object,
                default: () => {
                    return {}
                }
            }
        },
        methods: {
            itemSelect() {
                this.$emit('selectWorkshop', this.item.id);
            }
        },
    }
</script>

<style scoped>

</style>