import {ICacheOptions} from "@/types/interface";
import {isNullOrUndefined} from "@/utils/common";
import {CacheToken} from "@/constants/cacheKey";

const fix = "v1@"
/**
 * 缓存
 * @param isSessionStorage
 */
const cacheAdapter = (isSessionStorage?: boolean) => {
  return isSessionStorage ? sessionStorage : localStorage;
};

/**
 * 获取缓存值
 * @param key
 * @param options
 * @param defaultValue
 */
export const getCache = (key: string, options?: ICacheOptions, defaultValue?: unknown): any => {
  key = fix + key;
  options = { isParse: true, isDelete: false, ...options };
  try {
    const value = cacheAdapter(options.isSessionStorage).getItem(key);
    if (options.isDelete) {
      cacheAdapter(options.isSessionStorage).removeItem(key);
    }

    if (isNullOrUndefined(value)) {
      return defaultValue
    } else {
      if (options.isParse) {
        return value ? JSON.parse(value) : defaultValue;
      } else {
        return value;
      }
    }

  } catch (error) {
    console.log("getCache", error);
    return defaultValue;
  }
}

/**
 * 设置缓存值
 * @param key
 * @param value
 * @param isSessionStorage
 */
export const setCache = (
  key: string,
  value: string | Record<string, unknown> | Array<any>[],
  isSessionStorage?: boolean
): void => {
  key = fix + key;
  cacheAdapter(isSessionStorage).setItem(key, typeof value === "object" ? JSON.stringify(value) : value);
};

/**
 * 清除缓存值
 * @param key
 * @param isSessionStorage
 */
export const removeCache = (key: string, isSessionStorage?: boolean): void => {
  key = fix + key;
  cacheAdapter(isSessionStorage).removeItem(key);
}

export const getToken = (): string => {
  return getCache(CacheToken, { isSessionStorage: true }, {})["token"];
}