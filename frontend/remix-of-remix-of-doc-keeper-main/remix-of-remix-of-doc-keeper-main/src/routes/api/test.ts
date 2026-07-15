import { createFileRoute } from "@tanstack/react-router";
import axios from "axios";

export const Route = createFileRoute("/api/test")({
  server: {
    handlers: {
      GET: async () => {
        const res = await axios.get("https://api.github.com/zen");
        return Response.json({ data: res.data });
      },
    },
  },
});
