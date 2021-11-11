<template>
    <div class="basic-popup-body container-fluid">
        <div class="row my-1 mx-2" v-if="dataItem.id !== undefined">
            <div class="col-4 mr-2 ">Id:</div>
            <b-input
                    class="col-sm text-secondary bg-light border rounded-lg"
                    readonly
                    :value="dataItem.id"
                    v-model="dataItem.id"
            />
        </div>

        <div
                class="row my-1 mx-2"
                v-for="key of Object.keys(dataItem)"
                :key="key"

        >
            <div class="col-4 mr-2 ">{{key}}</div>
            <ul
                    class="col-sm text-secondary bg-light border rounded-lg"
                    readonly
                    v-if="Array.isArray(dataItem[key])"

            >
                <template v-if="dataItem[key].length > 0">
                    <li v-for="value of dataItem[key]"
                        :role="value"
                        :key="value.name"
                        style="list-style-type: none"
                    >
                        {{value.name}}
                    </li>
                </template>
                <template v-else>
                    <li style="list-style-type: none">
                        <pre><span> </span></pre>
                    </li>
                </template>
            </ul>
            <b-input
                    class="col-sm text-secondary bg-light border rounded-lg"
                    readonly
                    v-else
                    :value="dataItem[key]"
                    v-model="dataItem[key]"
            />
        </div>
    </div>
</template>

<script>
    export default {
        name: "BasicModalView",
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
        computed: {
        },
        methods: {
            returnItem() {
                this.$emit('returnItem', this.dataItem);
            }
        }

    }
</script>

<style scoped>

</style>