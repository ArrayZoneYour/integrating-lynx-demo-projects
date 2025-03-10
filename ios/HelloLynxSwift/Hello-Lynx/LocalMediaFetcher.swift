//
//  LocalMediaFetcher.swift
//  Hello-Lynx
//
//  Created by Yidong Li on 2025/3/10.
//

class LocalMediaFetcher: NSObject, LynxMediaResourceFetcher {
    func shouldRedirectUrl(_ request: LynxResourceRequest) -> String {
        let url = URL(fileURLWithPath: request.url)
        let fileName = url.lastPathComponent
        if (request.url.starts(with: "/")) {
            let resourcePath = Bundle.main.url(forResource: fileName, withExtension: nil, subdirectory: "")
            return (resourcePath != nil) ? "file://" + resourcePath!.absoluteString : ""
        } else {
            return request.url
        }
    }
    
    func isLocalResource(_ url: URL) -> LynxResourceOptionalBool {
        if (url.path().starts(with: "/")) {
            return LynxResourceOptionalBool.true
        } else {
            return LynxResourceOptionalBool.false
        }
    }
}
