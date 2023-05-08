// font-awesome 필수 라이브러리
import { library } from "@fortawesome/fontawesome-svg-core";

// 사용할 icons import
import {
  faPlaneDeparture,
  faMagnifyingGlass,
} from "@fortawesome/free-solid-svg-icons";
import { faStar, faHeart } from "@fortawesome/free-regular-svg-icons";

library.add(faPlaneDeparture, faMagnifyingGlass, faStar, faHeart);
