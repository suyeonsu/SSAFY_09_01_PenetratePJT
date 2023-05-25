<template>
  <div class="input-component">
    <div class="container">
      <label>
        <div :class="{ title: true, filled }">
          <slot />
        </div>
        <input
          :class="{ 'input-element': true, error }"
          :type="type"
          :value="msg"
          :readonly="readonly"
          :required="required"
          :disabled="disabled"
          @input="inputHandler" />
      </label>
      <div v-if="requiredError" class="error-required">
        {{ requiredErrorMessage }}
      </div>
    </div>
    <div v-if="errorMsg" class="error-message">{{ errorMsg }}</div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      filled: false,
      msg: "",
      requiredErrorMessage: "필수 항목입니다",
      requiredError: false,
    };
  },
  props: {
    type: {
      // input type
      type: String,
      default: "text",
    },
    errorMsg: {
      // 에러 메세지
      type: String,
      default: "",
    },
    required: {
      type: Boolean,
      default: false,
    },
    defaultMessage: {
      type: String,
      default: "",
    },
    disabled: {
      type: Boolean,
      default: false,
    },
    readonly: {
      type: Boolean,
      default: false,
    },
  },
  computed: {
    error() {
      if (this.requiredError) {
        return true;
      }
      return this.errorMsg === "" ? false : true;
    },
  },
  created() {
    this.msg = this.defaultMessage;
  },
  watch: {
    msg(nowMsg) {
      // input 내부의 메세지 입력 감지
      if (nowMsg === "") {
        this.filled = false;
        if (this.required) this.requiredError = true;
      } else {
        this.filled = true;
        if (this.required) this.requiredError = false;
      }
    },
  },
  methods: {
    inputHandler(event) {
      // 이중 동적 바인딩 용 이벤트 핸들러
      // v-model을 사용하지 않은 이유는 한글 입력을 제대로 감지하기 위함.
      this.msg = event.target.value; // 이중 동적 바인딩
      this.$emit("changeValue", this.msg); // 부모 요소에 msg 값 전달
    },
  },
};
</script>

<style lang="scss" scoped>
.input-component {
  .container {
    position: relative;

    .title {
      position: absolute;
      left: 1rem;
      top: 50%;
      transform: translateY(-50%);
      font-size: inherit;
      transition: 0.1s;
      cursor: text;
      &.filled {
        font-size: 0.3rem;
        top: 10%;
        transform: none;
      }
    }
    .input-element {
      // input 요소
      width: 100%;
      padding: 1rem;
      border: 1px solid lightgrey;
      box-sizing: border-box;
      &:hover {
        border: 1px solid $primary;
      }
      &:focus {
        border: 1px solid $primary;
        outline: none;
      }
      &:disabled,
      &:read-only {
        background: lightgray;
      }
      &.error {
        // 에러 발생 시 input 요소
        border: 1px solid $warning;
      }
    }
    .error-required {
      // required 에러 메세지
      color: $warning;
      position: absolute;
      right: 1rem;
      top: 50%;
      transform: translateY(-50%);
    }
  }
  .error-message {
    // 일반 에러 메세지
    color: $warning;
  }
}
</style>
