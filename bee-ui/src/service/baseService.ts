import {IHttpResponse, IObject} from "@/types/interface";
import http from "../utils/http";

/**
 * 常用REST请求
 */
export default {

  /**
   * 删除
   * @param path
   * @param params
   */
  delete(path: string, params: IObject): Promise<IHttpResponse> {
    return http({
      url: path,
      data: params,
      method: "DELETE"
    });
  },

  /**
   * GTE请求
   * @param path
   * @param params
   * @param headers
   */
  get(path: string, params?: IObject, headers?: IObject): Promise<IHttpResponse> {
    return new Promise((resolve, reject) => {
      http({
        url: path,
        params,
        headers,
        method: "GET"
      })
        .then(resolve)
        .catch((error) => {
          if (error != "-999") {
            reject(error);
          }
        });
    });
  },
  /**
   * PUT请求
   * @param path
   * @param params
   * @param headers
   */
  put(path: string, params?: IObject, headers?: IObject): Promise<IHttpResponse> {
    return http({
      url: path,
      data: params,
      headers: {
        "Content-Type": "application/json;charset=UTF-8",
        ...headers
      },
      method: "PUT"
    });
  },

  /**
   * POST请求
   * @param path
   * @param body
   * @param headers
   */
  post(path: string, body?: IObject, headers?: IObject): Promise<IHttpResponse> {
    return http({
      url: path,
      data: body,
      headers: {
        "Content-Type": "application/json;charset=UTF-8",
        ...headers
      },
      method: "POST"
    })
  }



}