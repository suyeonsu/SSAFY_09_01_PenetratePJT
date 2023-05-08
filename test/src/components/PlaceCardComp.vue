<template>
  <li class="item">
    <div class="image">
      <img :src="item.imageURL" alt="이미지" />
    </div>
    <div class="contents">
      <h3>{{ item.title }}</h3>
      <h4>{{ item.subTitle }}</h4>
      <address>
        <div>{{ item.address }}</div>
      </address>
    </div>
    <div class="menu">
      <div>
        {{ item.hearts }}
        <span>
          <font-awesome-icon :icon="['far', 'heart']" />
        </span>
      </div>
      <div>
        {{ item.stars }}
        <span>
          <font-awesome-icon :icon="['far', 'star']" />
        </span>
      </div>
    </div>
  </li>
</template>

<script>
export default {
  props: {
    item: {
      title: String,
      subTitle: String,
      address: String,
      hearts: Number,
      stars: Number,
      imageURL: URL,
    },
  },
};
</script>

<style lang="scss" scoped>
.item {
  margin-bottom: 2rem;
  box-sizing: border-box;
  .contents {
    // 리스트 아이템
    padding: 10px 0;
    h3 {
      // 아이템 제목
      font-size: 1.5rem;
      font-weight: bold;
      margin-bottom: 10px;
    }
    h4 {
      // 아이템 부제목
      font-size: 1.3rem;
      margin-bottom: 1rem;
    }
    address {
      word-break: keep-all;
    }
  }
  .menu {
    display: flex;
    padding: 1rem 0;
    background-color: $secondary;
    div {
      width: 50%;
      display: flex;
      justify-content: center;
      span {
        margin-left: 10px;
      }
    }
  }
  .image {
    width: 100%;
    height: 20vh;
    img {
      width: 100%;
      height: 100%;
    }
  }
}
</style>
