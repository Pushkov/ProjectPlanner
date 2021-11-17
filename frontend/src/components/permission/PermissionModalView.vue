<template>
    <div class="basic-popup-body container-fluid">

        <div class="row my-1 mx-2">
            <div class="col-4 mr-2 ">Имя:</div>
            <b-input
                    class="col-sm text-secondary  border rounded-lg"
                    :readonly="!isEdit"
                    :value="dataItem.name"
                    v-model="dataItem.name"/>
        </div>
        <div class="row my-1 mx-2">
            <div class="col-4 mr-2 ">Описание:</div>
            <b-input
                    class="col-sm text-secondary  border rounded-lg"
                    :readonly="!isEdit"
                    :value="dataItem.comment"
                    v-model="dataItem.comment"/>
        </div>
    </div>
</template>

<script>
    export default {
        name: "PermissionModalView",
        data() {
            return {
                dataItem: {...this.item}
            }
        },
        props: {
            item: {
                type: Object,
                default: () => {
                    return {}
                }
            },
            isEdit: {
                type: Boolean,
                default: false
            }
        },
        computed: {},
        methods: {
            returnItem() {
                this.$emit('returnItem', this.dataItem);
            }
        }

    }
</script>

<style scoped>

</style>