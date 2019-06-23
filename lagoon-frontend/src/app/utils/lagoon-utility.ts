import { HttpResponse } from "@angular/common/http";

export class UtilityClass {

    private UtilityClass() {

    }

    public static getFilename(response: HttpResponse<Blob>) {
        var filename = "";
        var disposition = response.headers.get("content-disposition");
        if (disposition && disposition.indexOf('attachment') !== -1) {
            var filenameRegex = /filename[^;=\n]*=((['"]).*?\2|[^;\n]*)/;
            var matches = filenameRegex.exec(disposition);
            if (matches != null && matches[1]) {
                filename = matches[1].replace(/['"]/g, '');
            }
        }
        return filename;
    }

    public static downloadFile(filename: string, blob: Blob) {
        const downloadLink = document.createElement("a");
        downloadLink.style.display = "none";
        document.body.appendChild(downloadLink);
        downloadLink.setAttribute("href", window.URL.createObjectURL(blob));
        downloadLink.setAttribute("download", filename);
        downloadLink.click();
        document.body.removeChild(downloadLink);
    }
}