<template>
    <div :class="isSecondModalWrapper" ref="ref-popup-wrapper">
        <div :class="isSecondModal">
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
            },
            isSecond: {
                type: Boolean,
                default: false
            }
        },
        computed: {
            isSecondModal() {
                return this.isSecond ? 'basic-popup-second' : 'basic-popup';
            },
            isSecondModalWrapper() {
                return this.isSecond ? 'basic-popup-wrapper-second' : 'basic-popup-wrapper';
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

    .basic-popup-wrapper-second {
        display: flex;
        position: absolute;
        top: 200px;
        left: 600px;
        right: 0;
        bottom: 0;
        justify-content: center;
        align-items: center;
    }

    .basic-popup {
      padding: 16px;
      width: 600px;
      background: whitesmoke;
      box-shadow: 10px 15px 15px black;
      border-radius: 10px;
      z-index: 15;
    }

    .basic-popup-second {
        padding: 16px;
        width: 400px;
        background: whitesmoke;
        box-shadow: 10px 15px 15px black;
        border-radius: 10px;
        z-index: 20;
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