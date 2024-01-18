export interface IFunction<T = any> {
  (x?: any): T;
}

export interface IObject<T = any> {
  [key: string]: T;
}

export interface IHttpResponse {
  code: number;
  msg: string;
  data: any;
}

/**
 * 菜单部分
 */
export interface IServerMenus {
  createDate: string;
  icon: string | boolean;
  id: string;
  name: string;
  parentName: string;
  permissions: string;
  pid: string;
  sort: number;
  type: number;
  url: string;
  openStyle: number;
  redirect?: string;
  children?: IServerMenus[]
}

export interface ICacheOptions {
  /**
   * 是否取值后立即删除缓存
   */
  isDelete?: boolean;
  /**
   * 是否采用JSON格式缓存
   */
  isParse?: boolean;
  /**
   * 是否采用会话缓存
   */
  isSessionStorage?: boolean;
}

export interface IViewHooksOptions {
  /**
   * 当前页面是否在创建时，调用查询数据接口
   */
  createIsNeed?: boolean;
  /**
   * 当前页面是否在激活（进入）时，调用查询数据列表接口
   */
  activatedIsNeed?: boolean;
  /**
   * 数据列表接口，API地址
   */
  getDataListURL?: string;
  /**
   * 数据列表，是否需要分页？
   */
  getDataListIsPage?: boolean;
  /**
   * 删除接口，API地址
   */
  deleteURL?: ""
  /**
   * 删除接口，是否批量删除
   */
  deleteIsBatch?: boolean;
  /**
   * 删除接口，批量状态下比较操作的key，如：uid，pid...
   */
  deleteIsBatchKey?: boolean;
  /**
   * 导出接口，api地址
   */
  exportURL?: string;

  /**
   * 查询条件
   */
  dataForm?: IObject;
  /**
   * 数据列表
   */
  dataList?: IObject[];
  /**
   * 排序，asc/desc
   */
  order?: string;
  /**
   * 排序字段
   */
  orderField?: string;
  /**
   * 当前页码
   */
  page?: number;
  /**
   * 每页数量
   */
  limit?: number;
  /**
   * 总条数
   */
  total?: number;
  /**
   * 数据列表，加载状态
   */
  dataListLoading?: boolean;
  /**
   * 数据列表，多选项
   */
  dataListSelections?: IObject[];
  elTable?: IObject;
}

export interface IViewHooks extends IViewHooksOptions, IObject {
  /**
   * 检查权限
   * @param key
   */
  hasPermission: (key: string) => boolean;
  /**
   * 获取字典名称
   * @param dictType
   * @param dictValue
   */
  getDictLabel: (dictType: string, dictValue: number) => string | number;
  /**
   * 查询列表记录
   */
  query: () => void;
  /**
   * 列表多选事件
   * @param list
   */
  dataListSelectionChangeHandle: (list: IObject[]) => void;
  /**
   * 列表排序事件
   * @param sort
   */
  dataListSortChangeHandle: (sort: IObject) => void;
  /**
   * 列表切换每页显示数量事件
   * @param pageSize
   */
  pageSizeChangeHandle: (pageSize: number) => void;
  /**
   * 列表分页事件
   * @param pageIndex
   */
  pageCurrentChangeHandle:(pageIndex: number) => void;
  /**
   * 列表搜索事件
   */
  getDataList: () => void;
  /**
   * 列表删除事件
   * @param id
   */
  deleteHandle: (id?: string) => Promise<any>
  /**
   * 列表导出事件
   */
  exportHandle: () => void;
  /**
   * 关闭当前Tab事件
   */
  closeCurrentTab: () => void;
  /**
   * 处理流程
   * @param e
   */
  handleFlowRoute: (e: IObject) => void;
  /**
   * 查看流程详情
   * @param e
   */
  flowDetailRoute: (e: IObject) => void;
}