<template>
    <div class="container-fluid">
        <div
                v-if="pages > 1 && pages <= 5"
                class="row w-75 m-auto">

            <div class="col-sm">
            </div>
            <div class="col-auto row">
                <div
                        class="col m-1 btn btn-outline-secondary"
                        @click="prevPage"
                >
                    <arrow-left-icon/>
                </div>
                <div
                        class="col m-1 btn btn-outline-secondary"
                        v-for="(p,index) in pages"
                        :key="index"
                        :class="p === currentPage ? ' border border-2' : ''"
                        @click="selectPage(index)"
                >
                    {{ p }}
                </div>
                <div
                        class="col m-1 btn btn-outline-secondary"
                        @click="nextPage"
                >
                    <arrow-right-icon/>
                </div>
            </div>
            <div class="col-sm">
            </div>
        </div>
        <div v-if="pages > 5" class="row w-75 m-auto">
            <div class="col-sm"/>
            <div class="col-1 m-1 btn btn-outline-secondary"
                 @click="firstPage"
            >
                <arrow-collapse-left-icon/>
            </div>
            <div class="col-1 m-1 btn btn-outline-secondary"
                 @click="prevPage"
            >
                <arrow-left-icon/>
            </div>
            <div class="col-1 m-1 btn "
                 v-if="currentPage <=  2"
            >
                &nbsp;
            </div>
            <div class="col-1 m-1 btn btn-outline-secondary"
                 v-else-if="currentPage > 3 "
            >
                ...
            </div>
            <div class="col-1 m-1 btn btn-outline-secondary"
                 v-else
                 @click="selectPage(currentPage - 3)"
            >
                {{ currentPage - 2 }}
            </div>
            <div class="col-1 m-1 btn btn-outline-secondary" v-if="currentPage >  1"
                 @click="selectPage(currentPage - 2)"
            >
                {{ currentPage - 1 }}
            </div>
            <div class="col-1 m-1 btn " v-else>
                &nbsp;
            </div>
            <input
                    ref="pageInput"
                    class=" col-1 m-1 form-control"
                    @keydown.enter.capture="selectPage2"
                    :value="currentPage"/>

            <div class="col-1 m-1 btn btn-outline-secondary"
                 v-if="pages - currentPage > 0"
                 @click="selectPage(currentPage )"
            >
                {{ currentPage + 1 }}
            </div>
            <div class="col-1 m-1 "
                 v-else
            >
                &nbsp;
            </div>


            <div class="col-1 m-1 btn btn-outline-secondary"
                 v-if="pages - currentPage > 3"
            >
                ...

            </div>
            <div class="col-1 m-1 btn btn-outline-secondary"
                 v-else-if="pages-currentPage > 1"
                 @click="selectPage(currentPage + 1)"
            >
                {{ currentPage + 2 }}
            </div>
            <div class="col-1 m-1 btn "
                 v-else
            >
                &nbsp;
            </div>
            <div class="col-1 m-1 btn btn-outline-secondary"
                 @click="nextPage"
            >
                <arrow-right-icon/>
            </div>
            <div class="col-1 m-1 btn btn-outline-secondary"
                 @click="lastPage"
            >
                <arrow-collapse-right-icon/>
            </div>
            <div class="col-sm"/>
        </div>
    </div>
</template>

<script>
    export default {
        name: "nic-pagination",
        props: {
            page: {
                type: Number,
                default: 0
            },
            pages: {
                type: Number,
                default: 1
            }
        },
        computed: {
            currentPage() {
                return this.page + 1;
            },
        },
        methods: {
            selectPage2() {
                let index = Number.parseInt(this.$refs.pageInput.value)
                if (isNaN(index) || index < 1) {
                    index = 1;
                } else if (index > this.pages) {
                    index = this.pages;
                }
                this.$emit('selectPage', index - 1);
            },
            selectPage(index) {
                this.$emit('selectPage', index);
            },
            prevPage() {
                let index = this.page - 1;
                if (index < 0)
                    index = 0;
                this.$emit('selectPage', index)
            },
            nextPage() {
                let index = this.page + 1;
                if (index >= this.pages - 1)
                    index = this.pages - 1;
                this.$emit('selectPage', index)
            },
            firstPage() {
                this.$emit('selectPage', 0)
            },
            lastPage() {
                this.$emit('selectPage', this.pages - 1)
            }
        }
    }
</script>

<style scoped>

</style>