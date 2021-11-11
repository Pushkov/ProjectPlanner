<template>
    <div class="basic-popup-wrapper" ref="ref-popup-wrapper">
        <div class="basic-popup">
            <div class="basic-popup-header">
                <span>{{ PopupTitle }}</span>
            </div>
            <slot></slot>
            <slot name="footer" class="basic-popup-footer">
                <span>
                <button
                        class="basic-btn-cansel text-black btn btn-outline-secondary rounded-lg"
                        @click="close"
                >
                    Close
                </button>
                </span>
            </slot>
        </div>
    </div>
</template>

<script>
    export default {
        name: "BasicModal",
        props: {
            PopupTitle: {
                type: String,
                default: 'Popup Title'
            },
            confirmBtnTitle: {
                type: String,
                default: 'OK'
            },
            isEdit: {
                type: Boolean,
                default: false
            }
        },
        methods: {
            close() {
                this.$emit('closeModal');
            }
        },
        mounted() {
            let vm = this;
            document.addEventListener('click', function (item) {
                if (item.target === vm.$refs['ref-popup-wrapper']) {
                    vm.close();
                }
            })
        }
    }
</script>

<style scoped>

    .basic-popup-wrapper {
        display: flex;
        position: absolute;
        top: 0;
        left: 0;
        right: 0;
        bottom: 0;
        justify-content: center;
        align-items: center;
        background: rgba(97, 97, 98, 0.6);
    }

    .basic-popup {
        padding: 16px;
        width: 600px;
        background: whitesmoke;
        box-shadow: 10px 15px 15px black;
        border-radius: 10px;
        z-index: 10;
    }

    .basic-popup-header {
        display: flex;
        justify-content: space-between;
        align-items: center;
        border-bottom: solid 1px darkgrey;
        /*border-top-right-radius: 10px;*/
        /*border-top-left-radius: 10px;*/
        padding: 10px;
    }

    .basic-popup-body {
        /*display: flex;*/
        /*flex-direction: column;*/
        /*justify-content: space-between;*/
        /*align-items: center;*/
        /*border-left: solid 1px darkgrey;*/
        /*border-right: solid 1px darkgrey;*/
        padding: 10px;
    }

    .basic-popup-footer {
        display: flex;
        justify-content: space-between;
        align-items: center;
        border-top: solid 1px darkgrey;
        /*border-bottom-right-radius: 10px;*/
        /*border-bottom-left-radius: 10px;*/
        padding: 10px;
    }

    .basic-btn-confirm, .nic-btn-cansel {
        cursor: pointer;
        /*padding: 5px 16px;*/
        /*border-radius: 5px;*/
        /*margin-right: 5px;*/
    }


    .material-icons {
        cursor: pointer;
    }
</style>