const baseURL = "http://localhost:9090/";

export function getToken() {
    const token = localStorage.getItem("token");
    var value = ""
    if (token !== null && token !== 'undefined') {
        value = token;
    }
    return value;
}

export function getObject(data) {
    var dataObj = JSON.stringify(data);
    if (isJson(data)) {
        dataObj = JSON.stringify(data);
    } else if (isString(data)) {
        dataObj = JSON.stringify(data);
    } else if (data instanceof Object) {
        dataObj = JSON.stringify(data);
    } else {
        dataObj = data;
    }
    return dataObj;
}

export function getData({ url, method, data = null }) {
    const getData = async () => {
        try {
            const token = getToken();
            const response = await fetch(baseURL + url,
                {
                    method: 'GET',
                    headers: {
                        Authorization: token ? `Bearer ${token}` : "",
                    },
                    data: data,
                }
            );
            // if (!response.ok) {
            //     throw new Error('Failed to getting data');
            // }
            return response;
        } catch (error) {
            console.error('Error getting data:', error);
        }
    };
    return getData();
}

export function postData({ url, method, data = null, isJson }) {
    const postData = async () => {
        try {
            const token = getToken();
            if (isJson) {
                const response = await fetch(baseURL + url,
                    {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json',
                            Authorization: token ? `Bearer ${token}` : "",
                        },
                        body: JSON.stringify(data)
                    }
                );
                // if (!response.ok) {
                //     throw new Error('Failed to posting data');
                // }
                return response;
            } else {
                const response = await fetch(baseURL + url,
                    {
                        method: 'POST',
                        headers: {
                            Authorization: token ? `Bearer ${token}` : "",
                        },
                        body: data
                    }
                );
                // if (!response.ok) {
                //     throw new Error('Failed to posting data');
                // }
                return response;
            }
        } catch (error) {
            console.error('Error posting data:', error);
        }
    };
    return postData();
}

export function deleteData({ url, method, data = null }) {
    const deleteData = async () => {
        try {
            const token = getToken();
            const response = await fetch(baseURL + url,
                {
                    method: 'DELETE',
                    headers: {
                        'Content-Type': 'application/json',
                        Authorization: token ? `Bearer ${token}` : "",
                    },
                    body: JSON.stringify(data),
                }
            );
            // if (!response.ok) {
            //     throw new Error('Failed to posting data');
            // }
            return response;
        } catch (error) {
            console.error('Error posting data:', error);
        }
    };
    return deleteData();
}


function isString(obj) {
    return typeof obj == 'string' || obj instanceof String;
}

function isJson(obj) {
    try {
        JSON.parse(obj);
        return true;
    } catch (e) {
        return false;
    }
}

function isBinary(obj) {
    return obj instanceof Blob || ArrayBuffer.isView(obj);
}
